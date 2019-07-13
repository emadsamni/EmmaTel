package com.example.emma_tel.fragments;

import androidx.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.emma_tel.R;
import com.example.emma_tel.utils.ProgressDialog;

import im.delight.android.webview.AdvancedWebView;

public class WebViewFragment extends Fragment implements AdvancedWebView.Listener{
    private AdvancedWebView webView;

    public WebViewFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_webview, container,false);
        assignUIReference(view);
        return  view;
    }
    private void assignUIReference(View view){
        webView = (AdvancedWebView) view.findViewById(R.id.webview);
        webView.setListener(getActivity(), WebViewFragment.this);
        webView.loadUrl("http://imei.sy/");
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        ProgressDialog.getInstance().show(getActivity());
    }

    @Override
    public void onPageFinished(String url) {
        ProgressDialog.getInstance().cancel();


    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        Toast.makeText(getActivity(), getResources().getString(R.string.internet_error), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
}
