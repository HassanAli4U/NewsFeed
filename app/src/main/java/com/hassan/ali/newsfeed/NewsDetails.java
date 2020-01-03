package com.hassan.ali.newsfeed;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        DataModel  model = (DataModel) getIntent().getBundleExtra("data").getSerializable("my object");

        String title= model.getTitle();
        String imageUrl1=model.getImageUrl1();
        String abstractt= model.getAbstractt();
        String link=model.getLink();
        String author=model.getAuthor();

        collapsingToolbar.setTitle(title);
        ImageView placePicutre = (ImageView) findViewById(R.id.image);
        Picasso.with(getApplicationContext()).load(imageUrl1).into(placePicutre);


        TextView authorTextView = (TextView) findViewById(R.id.author);
        authorTextView.setText(author+": ");


        TextView abstractTextView = (TextView) findViewById(R.id.abstractt);
        abstractTextView.setText(abstractt);

        TextView linkTextView =  (TextView) findViewById(R.id.link);
        String value = "<html>لمزيد من التفاصيل اضغط <a href=\""+link+"\">هنا</a></html>";

        linkTextView.setText(Html.fromHtml(value));
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

////        "for more details, keep free and browse here"
//linkTextView.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//    }
//});





    }


}
