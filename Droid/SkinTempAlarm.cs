using System;
using Android.Content;
using Android.App;
using Android.Widget;
using System.Linq;


namespace Moodband.Droid
{
	[BroadcastReceiver(Enabled = true)]
	public class SkinTempAlarm : BroadcastReceiver
	{

		public override async void OnReceive(Context context, Intent intent)
		{
			Moodband.BandManager bm = new BandManager();
			await bm.LogTemperature ();

			double[] temperatures = bm.ReadSkinTempsFromFile ();
			var message = intent.GetStringExtra("message");
			var title = intent.GetStringExtra("title") + string.Format(" - {0}", temperatures.Last ());
			var resultIntent = new Intent(context, typeof(MainActivity));
			resultIntent.SetFlags(ActivityFlags.NewTask | ActivityFlags.ClearTask);

			var pending = PendingIntent.GetActivity(context, 0, resultIntent, PendingIntentFlags.CancelCurrent);

			var builder =
				new Notification.Builder(context)
					.SetContentTitle(title)
					.SetContentText(message)
					.SetSmallIcon(Resource.Mipmap.Icon)
					.SetDefaults(NotificationDefaults.All);

			builder.SetContentIntent(pending);

			var notification = builder.Build();

			var manager = NotificationManager.FromContext(context);
			manager.Notify(1337, notification);
		}
	}
}
