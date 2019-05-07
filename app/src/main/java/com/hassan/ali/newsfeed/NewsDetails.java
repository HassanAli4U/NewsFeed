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
        String imageUrl4=model.getImageUrl4();
        String abstractt= model.getAbstractt();
        String byLine=model.getByLine();
        String link=model.getLink();

        collapsingToolbar.setTitle(title);
        ImageView placePicutre = (ImageView) findViewById(R.id.image);
        Picasso.with(getApplicationContext()).load(imageUrl4).into(placePicutre);
        TextView abstractTextView = (TextView) findViewById(R.id.abstractt);
        abstractTextView.setText(abstractt);

        TextView linkTextView =  (TextView) findViewById(R.id.link);
        String value = "<html>for more details, keep free and browse <a href=\""+link+"\">here</a></html>";

        linkTextView.setText(Html.fromHtml(value));
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

////        "for more details, keep free and browse here"
//linkTextView.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//    }
//});

        TextView byLineTextView =  (TextView) findViewById(R.id.byLine);
        byLineTextView.setTypeface(byLineTextView.getTypeface(), Typeface.BOLD_ITALIC);

        byLineTextView.setText(byLine);



    }


}
