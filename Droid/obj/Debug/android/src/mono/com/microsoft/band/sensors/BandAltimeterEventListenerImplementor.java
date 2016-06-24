package mono.com.microsoft.band.sensors;


public class BandAltimeterEventListenerImplementor
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.microsoft.band.sensors.BandAltimeterEventListener
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onBandAltimeterChanged:(Lcom/microsoft/band/sensors/BandAltimeterEvent;)V:GetOnBandAltimeterChanged_Lcom_microsoft_band_sensors_BandAltimeterEvent_Handler:Microsoft.Band.Sensors.IBandAltimeterEventListenerInvoker, Microsoft.Band.Android\n" +
			"";
		mono.android.Runtime.register ("Microsoft.Band.Sensors.IBandAltimeterEventListenerImplementor, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", BandAltimeterEventListenerImplementor.class, __md_methods);
	}


	public BandAltimeterEventListenerImplementor () throws java.lang.Throwable
	{
		super ();
		if (getClass () == BandAltimeterEventListenerImplementor.class)
			mono.android.TypeManager.Activate ("Microsoft.Band.Sensors.IBandAltimeterEventListenerImplementor, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}


	public void onBandAltimeterChanged (com.microsoft.band.sensors.BandAltimeterEvent p0)
	{
		n_onBandAltimeterChanged (p0);
	}

	private native void n_onBandAltimeterChanged (com.microsoft.band.sensors.BandAltimeterEvent p0);

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
