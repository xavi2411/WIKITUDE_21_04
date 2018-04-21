package com.example.xavier.wikitude_21_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public ArchitectView architectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.architectView = (ArchitectView)this.findViewById(R.id.architectView);
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey("pXWSeyXQSMu21cKxbx9rCvOH77rTxLtJ0t9HMLBivboXG9yQ5/8TtFq6EXXgKX8wX6cadbr3kd2tF4Ds3ZQAhuwmew9PlpzDNMXGM4H5jUMlj033iFpHGmTMUT7QqA0YBY2/k1mUtfePIp/nxTI6r+XWKI1IZyGiNexCYphQ6OtTYWx0ZWRfX/Dy4LNXpBq4tRojOjhTFxdFe7ANEzz9C6TIRA59pXPDT5ng0KtGU4PTcq3p4RrE+L0Wn7WOkiYmBTed0iHLsiabFWNvxsMcB3/XiM7f+zInwj28Sux4VdIgWtcc3WNK6xsKddS1R7Jh+ZkgPs2e+l6jrEZ13DcGfkoD5CMzSyGnnCfNpyi1dkNO2bWuJoz6+2H2mNFZc0sdIOW4lcYGH0PpVNIu7W16VnSGCJms5q1mWbofMl97QeM4T1q+SI4qGT0jqZS1djZzRAzb7ybhqxoOuCLfbzHBrpFoyTk/Xnb3jadD7YT6ZW8e2RZCyKAE3cBbjIFSvZT6Rz7hOnmFAhj3boRsgWPjRKnQdFd/3KbAmblck5ZrIj1Zit99cCEbZyyC8Q4anGIN62XwzlcAQjHErWYdQ2Aq9tDB/fbcUZh7IuJVjIqS2IqyG73M29/S2qpn8HPRguGFNVfAPATrbQu1IaWCGpDDUxZkxVAAssuO+bPugK88qug=");
        this.architectView.onCreate( config );
        this.architectView.onPostCreate();
        if (architectView.isDeviceSupported(this)) {

            try {
                this.architectView.load("http://xavidejuan.com/wikitude-sdk-samples/x_Demo_2_SolarSystem(Geo)/index.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
