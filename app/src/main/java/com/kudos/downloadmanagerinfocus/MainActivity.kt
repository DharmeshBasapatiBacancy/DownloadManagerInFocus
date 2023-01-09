package com.kudos.downloadmanagerinfocus

import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.kudos.downloadmanagerinfocus.Constants.MUSIC_URL
import com.kudos.downloadmanagerinfocus.Constants.PDF_URL
import com.kudos.downloadmanagerinfocus.Constants.PHOTO_URL
import com.kudos.downloadmanagerinfocus.Constants.TYPE_MUSIC
import com.kudos.downloadmanagerinfocus.Constants.TYPE_PDF
import com.kudos.downloadmanagerinfocus.Constants.TYPE_PHOTO
import com.kudos.downloadmanagerinfocus.Constants.TYPE_VIDEO
import com.kudos.downloadmanagerinfocus.Constants.VIDEO_URL
import com.kudos.downloadmanagerinfocus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var downloadManager: DownloadManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            downloadPhotoButton.setOnClickListener {
                startDownload(
                    PHOTO_URL,
                    TYPE_PHOTO
                )
            }
            downloadVideoButton.setOnClickListener {
                startDownload(
                    VIDEO_URL,
                    TYPE_VIDEO
                )
            }
            downloadPdfButton.setOnClickListener {
                startDownload(PDF_URL, TYPE_PDF)
            }
            downloadMusicButton.setOnClickListener {
                startDownload(
                    MUSIC_URL,
                    TYPE_MUSIC
                )
            }

        }
    }

    private fun startDownload(urlPath: String, type: String) {

        downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager

        val downloadRequest =
            DownloadManager.Request(Uri.parse(urlPath))
        with(downloadRequest) {
            setTitle("Focus Downloader")
            setDescription("$type is downloading...")
            setDestinationInExternalFilesDir(
                this@MainActivity,
                Environment.DIRECTORY_DOWNLOADS,
                "${type}_fileName"
            )
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        }

        downloadManager.enqueue(downloadRequest)

    }
}