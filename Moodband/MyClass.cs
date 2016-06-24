using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.Band.Portable;
//using Microsoft.Band.Personalization;
using Microsoft.Band.Portable.Sensors;
using Android.Widget;

namespace Moodband
{
	public class BandManager
	{
		private BandClient _bandClient;
		private bool _updated = false;
		private bool _updatedWrite = false;

		private string folder;
		private string skinTempModel;

		private Dictionary<string, BandTheme> bandThemes = new Dictionary<string, BandTheme>();

		public BandManager ()
		{
			folder = System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal);
			skinTempModel = Path.Combine(folder, "skinTempBuckets.txt");

			bandThemes["red"] = new BandTheme()
			{
				Base = BandColor.FromHex("ff6666"),
				HighContrast = BandColor.FromHex("ff3232"),
				Highlight = BandColor.FromHex("ff3232"),
				Lowlight = BandColor.FromHex("cc0000"),
				Muted = BandColor.FromHex("7f0000"),
				SecondaryText = BandColor.FromHex("666666"),
			};

			bandThemes["orange"] = new BandTheme()
			{
				Base = BandColor.FromHex("ffa500"),
				HighContrast = BandColor.FromHex("ffc966"),
				Highlight = BandColor.FromHex("ffc966"),
				Lowlight = BandColor.FromHex("cc8400"),
				Muted = BandColor.FromHex("ffdb99"),
				SecondaryText = BandColor.FromHex("666666"),
			};

			bandThemes["yellow"] = new BandTheme()
			{
				Base = BandColor.FromHex("ffff66"),
				HighContrast = BandColor.FromHex("ffff32"),
				Highlight = BandColor.FromHex("ffff32"),
				Lowlight = BandColor.FromHex("cccc00"),
				Muted = BandColor.FromHex("7f7f00"),
				SecondaryText = BandColor.FromHex("666666"),
			};

			bandThemes["green"] = new BandTheme()
			{
				Base = BandColor.FromHex("66ff66"),
				HighContrast = BandColor.FromHex("32ff32"),
				Highlight = BandColor.FromHex("32ff32"),
				Lowlight = BandColor.FromHex("00cc00"),
				Muted = BandColor.FromHex("007f00"),
				SecondaryText = BandColor.FromHex("666666"),
			};

			bandThemes["blue"] = new BandTheme()
			{
				Base = BandColor.FromHex("6666ff"),
				HighContrast = BandColor.FromHex("3232ff"),
				Highlight = BandColor.FromHex("3232ff"),
				Lowlight = BandColor.FromHex("0000cc"),
				Muted = BandColor.FromHex("00007f"),
				SecondaryText = BandColor.FromHex("666666"),
			};

			bandThemes["purple"] = new BandTheme()
			{
				Base = BandColor.FromHex("9370DB"),
				HighContrast = BandColor.FromHex("DDA0DD"),
				Highlight = BandColor.FromHex("DDA0DD"),
				Lowlight = BandColor.FromHex("4B0082"),
				Muted = BandColor.FromHex("D8BFD8"),
				SecondaryText = BandColor.FromHex("666666"),
			};
		}

		public async void UpdateColors() {
			var bandClientManager = BandClientManager.Instance;
			// query the service for paired devices
			var pairedBands = await bandClientManager.GetPairedBandsAsync();
			// connect to the first device
			var bandInfo = pairedBands.FirstOrDefault();
			_bandClient = await bandClientManager.ConnectAsync(bandInfo);
			GetTemperatureChangeTheme();
		}

		public async Task LogTemperature() {
			var bandClientManager = BandClientManager.Instance;
			// query the service for paired devices
			var pairedBands = await bandClientManager.GetPairedBandsAsync();
			// connect to the first device
			var bandInfo = pairedBands.FirstOrDefault();
			_bandClient = await bandClientManager.ConnectAsync(bandInfo);
			await GetTemperatureWriteFile ();
		}

		private async void GetTemperatureChangeTheme () {
			if (_bandClient != null) {
				var sensorManager = _bandClient.SensorManager;
				var skinTemp = sensorManager.SkinTemperature;
				skinTemp.ReadingChanged += ChangeTheme;

				// start reading, with the interval
				await skinTemp.StartReadingsAsync (BandSensorSampleRate.Ms16);
				while (!_updated) {
					await Task.Delay (TimeSpan.FromMilliseconds(100));
				}
				_updated = false;
				await skinTemp.StopReadingsAsync();
			}
		}

		private async void ChangeTheme(object sender, BandSensorReadingEventArgs<BandSkinTemperatureReading> e) {
			double temperatureCelsius = e.SensorReading.Temperature;
			_updated = true;

			// These are free bonus readings to add to our data (unless we want to deliberately discount them for throwing off data? ooh
			//WriteFile (sender, e);

			double average = 33;
			double sd = 1;

			// Get our model for figuring out temperatures
			if (File.Exists(skinTempModel)) {
				string[] model = File.ReadAllLines (skinTempModel);
				average = Double.Parse (model [0]);
				sd = Double.Parse (model [1]);
			}

			BandTheme theme = await _bandClient.PersonalizationManager.GetThemeAsync();

			BandTheme newTheme;

			if (temperatureCelsius > (average + (sd * 1.5)))
			{
				newTheme = bandThemes["red"];
			}
			else if (temperatureCelsius > (average + (sd * 0.5)))
			{
				newTheme = bandThemes["orange"];
			}
			else if (temperatureCelsius > average)
			{
				newTheme = bandThemes["yellow"];
			}
			else if (temperatureCelsius > (average - (sd * 0.5)))
			{
				newTheme = bandThemes["green"];
			}
			else if (temperatureCelsius > (average - (sd * 1.5)))
			{
				newTheme = bandThemes["blue"];
			}
			else {
				newTheme = bandThemes["purple"];
			}

			// set the new theme on the Band
			// TODO: Make this == check properties not object
			if (theme != newTheme) {
				await _bandClient.PersonalizationManager.SetThemeAsync(newTheme);
			}

			WriteFile(sender, e);

		}

		private async Task GetTemperatureWriteFile () {
			if (_bandClient != null) {
				var sensorManager = _bandClient.SensorManager;
				var skinTemp = sensorManager.SkinTemperature;
				skinTemp.ReadingChanged += WriteFile;

				// start reading, with the interval
				await skinTemp.StartReadingsAsync (BandSensorSampleRate.Ms16);
				while (!_updatedWrite) {
					await Task.Delay (TimeSpan.FromMilliseconds(100));
				}
				_updatedWrite = false;
				await skinTemp.StopReadingsAsync ();
			}
		}

		private void WriteFile(object sender, BandSensorReadingEventArgs<BandSkinTemperatureReading> e) {
			double temperatureCelsius = e.SensorReading.Temperature;
			var filename = Path.Combine(folder, "skinTemp.log");
			File.AppendAllText (filename, string.Format ("{0}\n", temperatureCelsius));

			CalculateAndWriteModel ();
			_updatedWrite = true;
		}

		private void CalculateAndWriteModel() {

			double[] temperatures = ReadSkinTempsFromFile ();

			double average = temperatures.Average ();
			double sumOfSquaresOfDifferences = temperatures.Select (val => (val - average) * (val - average)).Sum ();
			double sd = Math.Sqrt (sumOfSquaresOfDifferences / temperatures.Length); 
			File.WriteAllText (skinTempModel, string.Format (
				"{0}\n{1}\n{2}\n{3}",
				average, sd, temperatures.Length, temperatures.Last()));
		}

		public double[] ReadSkinTempsFromFile() {
			var filename = Path.Combine(folder, "skinTemp.log");

			string[] temps = File.ReadAllLines (filename);
			int numTemps = temps.Length;
			double[] temperatures = new double[numTemps];

			for (int i = 0; i < numTemps; i++) {
				temperatures [i] = Double.Parse(temps[i]);
			}

			return temperatures;
		}
	}
}

