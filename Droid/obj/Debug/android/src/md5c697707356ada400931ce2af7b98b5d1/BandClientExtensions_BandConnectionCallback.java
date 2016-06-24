package md5c697707356ada400931ce2af7b98b5d1;


public class BandClientExtensions_BandConnectionCallback
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.microsoft.band.BandConnectionCallback
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onStateChanged:(Lcom/microsoft/band/ConnectionState;)V:GetOnStateChanged_Lcom_microsoft_band_ConnectionState_Handler:Microsoft.Band.IBandConnectionCallbackInvoker, Microsoft.Band.Android\n" +
			"";
		mono.android.Runtime.register ("Microsoft.Band.BandClientExtensions+BandConnectionCallback, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", BandClientExtensions_BandConnectionCallback.class, __md_methods);
	}


	public BandClientExtensions_BandConnectionCallback () throws java.lang.Throwable
	{
		super ();
		if (getClass () == BandClientExtensions_BandConnectionCallback.class)
			mono.android.TypeManager.Activate ("Microsoft.Band.BandClientExtensions+BandConnectionCallback, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}


	public void onStateChanged (com.microsoft.band.ConnectionState p0)
	{
		n_onStateChanged (p0);
	}

	private native void n_onStateChanged (com.microsoft.band.ConnectionState p0);

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
