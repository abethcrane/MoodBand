using Android.App;
using Android.Widget;
using Android.OS;
using Android.Content;
using Android.Runtime;
using System.Linq;
using System;
using System.IO;

namespace Moodband.Droid
{
	[Activity (Label = "Moodband", MainLauncher = true, Icon = "@mipmap/icon")]
	public class MainActivity : Activity
	{
		Moodband.BandManager bm = new BandManager();
		string folder;
		string skinTempModel;

		protected override void OnCreate (Bundle savedInstanceState)
		{
			base.OnCreate (savedInstanceState);

			// Set our view from the "main" layout resource
			SetContentView (Resource.Layout.Main);

			// Get our button from the layout resource,
			// and attach an event to it
			Button setMoodButton = FindViewById<Button> (Resource.Id.setMoodButton);
			Button skinTempButton = FindViewById<Button> (Resource.Id.skinTempButton);
			Button skinTempStopButton = FindViewById<Button> (Resource.Id.skinTempStopButton);
			Button updateAverageButton = FindViewById<Button> (Resource.Id.updateAverageButton);

			folder = System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal);
			skinTempModel = Path.Combine(folder, "skinTempBuckets.txt");

			setMoodButton.Click += delegate {
				bm.UpdateColors();
				refreshAverageText();
			};

			skinTempButton.Click += delegate {
				var alarmIntent = new Intent(this, typeof(SkinTempAlarm));

				alarmIntent.PutExtra("title", "Moodband");
				alarmIntent.PutExtra("message", "Logging yo temperature");

				var pending = PendingIntent.GetBroadcast(this, 0, alarmIntent, PendingIntentFlags.UpdateCurrent);

				var alarmManager = GetSystemService(AlarmService).JavaCast<AlarmManager>();
				alarmManager.SetInexactRepeating(AlarmType.Rtc, 0, AlarmManager.IntervalFifteenMinutes, pending);
			};

			skinTempStopButton.Click += delegate {
				var alarmIntent = new Intent (this, typeof(SkinTempAlarm));
				PendingIntent intentStop = PendingIntent.GetBroadcast (this, 0, alarmIntent, PendingIntentFlags.UpdateCurrent);
				var alarmManager = GetSystemService (AlarmService).JavaCast<AlarmManager> ();
				alarmManager.Cancel (intentStop);


				CalculateAndWriteModel();
			};
		
			updateAverageButton.Click += delegate {
				refreshAverageText ();
			};

			refreshAverageText ();

		}

		private void refreshAverageText() {
			TextView tempInfo = FindViewById<TextView> (Resource.Id.temperatureInfo);
			CalculateAndWriteModel ();
			if (File.Exists (skinTempModel)) {
				string[] model = File.ReadAllLines (skinTempModel);
				double average = Double.Parse (model [0]);
				double sd = Double.Parse (model [1]);
				int numReadings = int.Parse (model [2]);
				double last = Double.Parse (model [3]);
				double min = Double.Parse(model[4]);
				double max = Double.Parse(model[5]);
				tempInfo.Text = string.Format (
					"Average temp: {0}\nLast temp: {1}\nStandard Deviation: {2}\nNum readings: {3}\nMin: {4}\nMax: {5}\n",
					average, last, sd, numReadings, min, max);
			} else {
				tempInfo.Text = "No readings yet, sorry :(";
			}
		}

		private void CalculateAndWriteModel() {

			double[] temperatures = bm.ReadSkinTempsFromFile ();

			double average = temperatures.Average ();
			double sumOfSquaresOfDifferences = temperatures.Select (val => (val - average) * (val - average)).Sum ();
			double sd = Math.Sqrt (sumOfSquaresOfDifferences / temperatures.Length);
			File.WriteAllText (skinTempModel, string.Format (
				"{0}\n{1}\n{2}\n{3}\n{4}\n{5}",
				average, sd, temperatures.Length, temperatures.Last(), temperatures.Min(), temperatures.Max()));
		}
	}
}


