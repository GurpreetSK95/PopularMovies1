package gurpreetsk.me.popularmovies1;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import gurpreetsk.me.popularmovies1.models.MovieData;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView vote_average, release_date, overview;

    private final String RATED = "Rated: ";
    private final String RELEASE_DATE = "Released: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getHandles();

        MovieData data = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);

        setTitle(data.getOriginal_title());

        vote_average.setText(RATED + data.getVote_average());
        release_date.setText(RELEASE_DATE + data.getRelease_date());
        overview.setText(data.getOverview());
        Uri builder = Uri.parse("http://image.tmdb.org/t/p/w185/").buildUpon()
                .appendEncodedPath(data.getPoster_path())
                .build();
        Picasso.with(this).load(builder.toString()).fit().into(imageView);

    }

    public void getHandles() {
        imageView = (ImageView) findViewById(R.id.detail_image_view);
        vote_average = (TextView) findViewById(R.id.detail_vote_average);
        release_date = (TextView) findViewById(R.id.detail_release_date);
        overview = (TextView) findViewById(R.id.detail_overview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        MenuItem item =  menu.findItem(R.id.share_action_provider);
        ShareActionProvider sap = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra(MovieGridViewFragment.EXTRA_TITLE) + "#P1_Udacity");
        if (sap != null)
            sap.setShareIntent(intent);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
