package md5c697707356ada400931ce2af7b98b5d1;


public class BandResultCallback
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.microsoft.band.BandResultCallback
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onResult:(Ljava/lang/Object;Ljava/lang/Throwable;)V:GetOnResult_Ljava_lang_Object_Ljava_lang_Throwable_Handler:Microsoft.Band.IBandResultCallbackInvoker, Microsoft.Band.Android\n" +
			"";
		mono.android.Runtime.register ("Microsoft.Band.BandResultCallback, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", BandResultCallback.class, __md_methods);
	}


	public BandResultCallback () throws java.lang.Throwable
	{
		super ();
		if (getClass () == BandResultCallback.class)
			mono.android.TypeManager.Activate ("Microsoft.Band.BandResultCallback, Microsoft.Band.Android, Version=1.3.20307.2, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}


	public void onResult (java.lang.Object p0, java.lang.Throwable p1)
	{
		n_onResult (p0, p1);
	}

	private native void n_onResult (java.lang.Object p0, java.lang.Throwable p1);

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
