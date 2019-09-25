package com.reactlibrary;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.ReactActivity;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoPlayerActivity extends ReactActivity {

    private Boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private String videoUrl;
    private SimpleExoPlayer player;
    private DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

    private PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        playerView = findViewById(R.id.exoPlayerView);
        setVideoUrlFromIntent();
        initializePlayer();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setVideoUrlFromIntent() {
        videoUrl = getIntent().getStringExtra("videoUrl");
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void initializePlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(this,
                    new DefaultTrackSelector()
            );
            playerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
        }
        MediaSource mediaSource = buildMediaSource(Uri.parse(videoUrl));
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        String extension = uri.toString().substring(uri.toString().lastIndexOf("."));
        if (extension.equals(".m3u8")) {
            DataSource.Factory dataSourceFactory =
                    new DefaultHttpDataSourceFactory(Util.getUserAgent(this, "RNVideoPlayer"));

            return new HlsMediaSource.Factory(dataSourceFactory)
                    .setAllowChunklessPreparation(true)
                    .createMediaSource(uri);
        }

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "RNVideoPlayer"));

        return new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }
}
