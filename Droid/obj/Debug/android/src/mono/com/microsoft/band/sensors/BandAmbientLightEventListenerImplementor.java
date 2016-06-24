package mono.com.microsoft.band.sensors;


public class BandAmbientLightEventListenerImplementor
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.microsoft.band.sensors.BandAmbientLightEventListener
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onBandAmbientLightChanged:(Lcom/microsoft/band/sensors/BandAmbientLightEvent;)V:GetOnBandAmbientLightChanged_Lcom_microsoft_band_sensors_BandAmbientLightEvent_Handler:Microsoft.Band.Sensors.IBandAmbientLightEventListenerInvoker, Microsoft.Band.Android\n" +
			"";
		mono.android.Runtime.register ("Microsoft.Band.Sensors.IBandAmbientLightEventListenerImplementor, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", BandAmbientLightEventListenerImplementor.class, __md_methods);
	}


	public BandAmbientLightEventListenerImplementor () throws java.lang.Throwable
	{
		super ();
		if (getClass () == BandAmbientLightEventListenerImplementor.class)
			mono.android.TypeManager.Activate ("Microsoft.Band.Sensors.IBandAmbientLightEventListenerImplementor, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}


	public void onBandAmbientLightChanged (com.microsoft.band.sensors.BandAmbientLightEvent p0)
	{
		n_onBandAmbientLightChanged (p0);
	}

	private native void n_onBandAmbientLightChanged (com.microsoft.band.sensors.BandAmbientLightEvent p0);

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
