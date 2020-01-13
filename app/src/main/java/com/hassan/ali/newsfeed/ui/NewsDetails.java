package com.hassan.ali.newsfeed.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.hassan.ali.newsfeed.R;
import com.hassan.ali.newsfeed.retrofit.ArticlesResponse;
import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        // collapsingToolbar.setTitle(getString(R.string.item_title));
        ArticlesResponse.ArticlesBean model = (ArticlesResponse.ArticlesBean) getIntent().getBundleExtra("data").getSerializable("my object");

        String title= model.getTitle();
        String imageUrl1=model.getUrlToImage();
        String abstractt= model.getDescription();
        String link=model.getUrl();
        String author=model.getAuthor();

        collapsingToolbar.setTitle(title);
        ImageView placePicutre = (ImageView) findViewById(R.id.image);
        Picasso.with(getApplicationContext()).load(imageUrl1).into(placePicutre);


        TextView authorTextView = (TextView) findViewById(R.id.author);
        authorTextView.setText(author);


        TextView descriptionTextView = (TextView) findViewById(R.id.description);
        descriptionTextView.setText(abstractt);

        TextView urlTextView =  (TextView) findViewById(R.id.url);
        String value = "<html>لمزيد من التفاصيل اضغط <a href=\""+link+"\">هنا</a></html>";

        urlTextView.setText(Html.fromHtml(value));
        urlTextView.setMovementMethod(LinkMovementMethod.getInstance());

////        "for more details, keep free and browse here"
//linkTextView.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//    }
//});





    }

    // Handle back button in the toolbar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
