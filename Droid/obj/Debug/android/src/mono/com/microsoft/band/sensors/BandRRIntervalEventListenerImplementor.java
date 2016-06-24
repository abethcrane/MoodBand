package mono.com.microsoft.band.sensors;


public class BandRRIntervalEventListenerImplementor
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.microsoft.band.sensors.BandRRIntervalEventListener
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onBandRRIntervalChanged:(Lcom/microsoft/band/sensors/BandRRIntervalEvent;)V:GetOnBandRRIntervalChanged_Lcom_microsoft_band_sensors_BandRRIntervalEvent_Handler:Microsoft.Band.Sensors.IBandRRIntervalEventListenerInvoker, Microsoft.Band.Android\n" +
			"";
		mono.android.Runtime.register ("Microsoft.Band.Sensors.IBandRRIntervalEventListenerImplementor, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", BandRRIntervalEventListenerImplementor.class, __md_methods);
	}


	public BandRRIntervalEventListenerImplementor () throws java.lang.Throwable
	{
		super ();
		if (getClass () == BandRRIntervalEventListenerImplementor.class)
			mono.android.TypeManager.Activate ("Microsoft.Band.Sensors.IBandRRIntervalEventListenerImplementor, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}


	public void onBandRRIntervalChanged (com.microsoft.band.sensors.BandRRIntervalEvent p0)
	{
		n_onBandRRIntervalChanged (p0);
	}

	private native void n_onBandRRIntervalChanged (com.microsoft.band.sensors.BandRRIntervalEvent p0);

	private java.util.ArrayList refList;
	public void monodroidAddReference (java.lang.Object obj)
	{
		if (refList == null)
			refList = new java.util.ArrayList ();
		refList.add (obj);
	}

	public void monodroidClearReferences ()
	{
		if (refList != null)
			refList.clear ();
	}
}
