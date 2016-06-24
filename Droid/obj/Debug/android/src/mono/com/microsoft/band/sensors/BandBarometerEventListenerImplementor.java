package mono.com.microsoft.band.sensors;


public class BandBarometerEventListenerImplementor
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.microsoft.band.sensors.BandBarometerEventListener
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onBandBarometerChanged:(Lcom/microsoft/band/sensors/BandBarometerEvent;)V:GetOnBandBarometerChanged_Lcom_microsoft_band_sensors_BandBarometerEvent_Handler:Microsoft.Band.Sensors.IBandBarometerEventListenerInvoker, Microsoft.Band.Android\n" +
			"";
		mono.android.Runtime.register ("Microsoft.Band.Sensors.IBandBarometerEventListenerImplementor, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", BandBarometerEventListenerImplementor.class, __md_methods);
	}


	public BandBarometerEventListenerImplementor () throws java.lang.Throwable
	{
		super ();
		if (getClass () == BandBarometerEventListenerImplementor.class)
			mono.android.TypeManager.Activate ("Microsoft.Band.Sensors.IBandBarometerEventListenerImplementor, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}


	public void onBandBarometerChanged (com.microsoft.band.sensors.BandBarometerEvent p0)
	{
		n_onBandBarometerChanged (p0);
	}

	private native void n_onBandBarometerChanged (com.microsoft.band.sensors.BandBarometerEvent p0);

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
