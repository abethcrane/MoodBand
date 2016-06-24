package mono.com.microsoft.band.sensors;


public class BandGsrEventListenerImplementor
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.microsoft.band.sensors.BandGsrEventListener
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onBandGsrChanged:(Lcom/microsoft/band/sensors/BandGsrEvent;)V:GetOnBandGsrChanged_Lcom_microsoft_band_sensors_BandGsrEvent_Handler:Microsoft.Band.Sensors.IBandGsrEventListenerInvoker, Microsoft.Band.Android\n" +
			"";
		mono.android.Runtime.register ("Microsoft.Band.Sensors.IBandGsrEventListenerImplementor, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", BandGsrEventListenerImplementor.class, __md_methods);
	}


	public BandGsrEventListenerImplementor () throws java.lang.Throwable
	{
		super ();
		if (getClass () == BandGsrEventListenerImplementor.class)
			mono.android.TypeManager.Activate ("Microsoft.Band.Sensors.IBandGsrEventListenerImplementor, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}


	public void onBandGsrChanged (com.microsoft.band.sensors.BandGsrEvent p0)
	{
		n_onBandGsrChanged (p0);
	}

	private native void n_onBandGsrChanged (com.microsoft.band.sensors.BandGsrEvent p0);

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
