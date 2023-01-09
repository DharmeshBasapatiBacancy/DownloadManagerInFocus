package com.kudos.downloadmanagerinfocus

import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
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
                    "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png",
                    "Photo"
                )
            }
            downloadVideoButton.setOnClickListener {
                startDownload(
                    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                    "Video"
                )
            }
            downloadPdfButton.setOnClickListener {
                startDownload("https://clickdimensions.com/links/TestPDFfile.pdf", "PDF")
            }
            downloadMusicButton.setOnClickListener {
                startDownload(
                    "https://www.kozco.com/tech/LRMonoPhase4.mp3",
                    "Music"
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