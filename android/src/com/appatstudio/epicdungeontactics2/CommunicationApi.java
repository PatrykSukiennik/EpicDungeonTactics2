package com.appatstudio.epicdungeontactics2;

final class CommunicationApi {

    public boolean checkNetworkConnection() {
        return AndroidLauncher.isInternetConnectionUp;
    }

    public void generateInterstitialAd() {
        AndroidLauncher.isAdWanted = true;
    }
}
