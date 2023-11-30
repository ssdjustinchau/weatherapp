package hk.edu.hkmu.weatherreport;


import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import hk.edu.hkmu.weatherreport.BaseActivity;



public class MultimediaActivity extends BaseActivity {

    private WebView webView1, webView2, webView3;
    private String videoUrl1, videoUrl2, videoUrl3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        webView1 = findViewById(R.id.video1);
        webView2 = findViewById(R.id.video2);
        webView3 = findViewById(R.id.video3);

        videoUrl1 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XpO91uyU2BY?si=wlGgAspMJtJpyuN-\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        videoUrl2 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/F1n-jYKcD3g?si=UQtDvAmKiRL_IoX1\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        videoUrl3 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/lPHvZZlZtEw?si=v_ZK1pdp1fKbp_7M\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";

        loadVideo(videoUrl1, webView1);
        loadVideo(videoUrl2, webView2);
        loadVideo(videoUrl3, webView3);

    }

    private void loadVideo(String url, WebView webView) {
        webView.loadData(url, "text/html","utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());;
    }


}