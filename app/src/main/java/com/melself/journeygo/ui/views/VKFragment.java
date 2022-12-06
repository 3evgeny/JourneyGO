package com.melself.journeygo.ui.views;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.melself.journeygo.R;
import com.melself.journeygo.data.ConfigUser;
import com.melself.journeygo.databinding.FragmentVKBinding;
import com.melself.journeygo.ui.viewmodels.VKViewModel;

public class VKFragment extends Fragment {

    private VKViewModel mViewModel;
    FragmentVKBinding binding;
    private String url;

    public static VKFragment newInstance() {
        return new VKFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVKBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VKViewModel.class);
        // TODO: Use the ViewModel
        url = "https://oauth.vk.com/authorize?client_id=51492140&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=email&response_type=token&v=5.131&state=123456";
        CookieManager.getInstance().removeAllCookies(null);
        binding.web.clearCache(true);
        binding.web.loadUrl(url);
        binding.web.setWebViewClient(getWebViewClient());
    }


    public WebViewClient getWebViewClient(){
        return new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String urlString = request.getUrl().toString();
                if(urlString.contains("https://oauth.vk.com/blank.html")){
                    System.out.println(urlString);
                    ConfigUser.getInstance().access_token = Uri.parse(urlString.replace("#", "?")).getQueryParameter("access_token");
                    ConfigUser.getInstance().email = Uri.parse(urlString.replace("#", "?")).getQueryParameter("email");
                    ConfigUser.getInstance().user_id = Uri.parse(urlString.replace("#", "?")).getQueryParameter("user_id");
                    replaceFragment(new VkSignInFragment());
                    return false;
                }
                view.loadUrl(urlString);
                return true;
            }
        };
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameAuth, fragment);
        ft.commit();
    }
}