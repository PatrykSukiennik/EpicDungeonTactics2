package com.appatstudio.epicdungeontactics2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AndroidLauncher extends AndroidApplication {

	private static final String TAG = "Epic Dungeon Tactics 2";
	private static final String adBANNER_UNIT_ID = "ca-app-pub-1351360411950245/3235369322";
	private static final String adINTERSTITIAL_ID = "ca-app-pub-1351360411950245/6983042643";
	private static final String adAPP_ID = "ca-app-pub-1351360411950245~7331840089";

	private boolean isGamePaused = false;
	public static boolean isInternetConnectionUp;
	private RelativeLayout relativeLayout;

	private Handler handler0;
	private Handler handler1;
	private Handler handler2;

	private float delay1;
	public static boolean isAdWanted;
	public static InterstitialAd interstitialAd;

	protected AdView adView;
	public static AdRequest.Builder builder;

	private static Bundle extras;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		extras = new Bundle();
		//extras.putBoolean("tag_for_under_age_of_consent", true);
		//extras.putString("max_ad_content_rating", "G"); todo

		isAdWanted = false;

		config.useImmersiveMode = true;
		config.hideStatusBar = true;
		config.r = 8;
		config.g = 8;
		config.b = 8;
		config.a = 8;

		relativeLayout = new RelativeLayout(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);


		relativeLayout = new RelativeLayout(this);

		View gameView = initializeForView(new EpicDungeonTactics(new CommunicationHandler()), config);
		relativeLayout.addView(gameView);

		MobileAds.initialize(this, adAPP_ID);
		initBannerAd();

		final Handler handler = new Handler();
		final int delay0 = 10000; //milliseconds
		handler1 = new Handler();
		handler2 = new Handler();
		delay1 = 100000; //milliseconds


		initInterAd();
		startRefreshingInterAd();
		startRefreshingInternetChecking();
		setContentView(relativeLayout);
	}

	@Override
	protected void onPause() {
		super.onPause();
		isGamePaused = true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		isGamePaused = false;
	}

	public void startRefreshingInternetChecking() {
		handler.postDelayed(new Runnable() {
			public void run() {
				if (!isGamePaused) {

					isInternetConnectionUp = isNetworkConnected();
					handler.postDelayed(this, 2000);


				}
			}
		}, 100);
	}

	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			// There are no active networks.
			return false;
		} else
			return true;
	}


	private void initBannerAd() {
		adView = new AdView(this);

		builder = new AdRequest.Builder();
		builder.addTestDevice("A7BD8B805E70CDB7DC572263DB571087");
		builder.addTestDevice("0FE985F346AD7D774CDFE6F6EE4C3936");
		builder.addTestDevice("D96A4A5609EC5E436C75480E5ACE948B");

		adView.setAdListener(new AdListener() {

			@Override
			public void onAdLoaded() {
				Log.i(TAG, "Ad loaded");
			}
		});

		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(adBANNER_UNIT_ID);

		RelativeLayout.LayoutParams adParams =
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

		relativeLayout.addView(adView, adParams);
		adView.loadAd(builder.
				addNetworkExtrasBundle(AdMobAdapter.class, extras).build());

	}

	public void initInterAd() {
		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId(adINTERSTITIAL_ID);

		interstitialAd.setAdListener(new AdListener() {

			public void onAdLoaded() {
				displayInterAd();
			}

			@Override
			public void onAdClosed() {
				super.onAdClosed();
				handler2.postDelayed(new Runnable(){
					public void run(){
						if (!isGamePaused) {

							if (isAdWanted) {
								isAdWanted = false;
								refreshInterAd();
							}
							handler2.postDelayed(this, 100);


						}
					}
				}, 100);

				startRefreshingInternetChecking();
			}
		});
	}

	public static void displayInterAd() {
		if (interstitialAd.isLoaded()) {
			interstitialAd.show();
		}
	}

	public void startRefreshingInterAd() {
		handler2.postDelayed(new Runnable(){
			public void run(){
				if (!isGamePaused) {

					if (isAdWanted) {
						isAdWanted = false;
						refreshInterAd();
					}
					handler2.postDelayed(this, 1000);


				}
			}
		}, 100);
	}

	public static void refreshInterAd() {
		interstitialAd.loadAd(builder.
				addNetworkExtrasBundle(AdMobAdapter.class, extras).build());
		displayInterAd();
	}



}
