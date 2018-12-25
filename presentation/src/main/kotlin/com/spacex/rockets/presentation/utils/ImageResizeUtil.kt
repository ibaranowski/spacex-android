package com.spacex.rockets.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import io.reactivex.Single
import timber.log.Timber
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

//FIXME Perhaps we should move it to data layer and make it more flexible
class ImageResizeUtil(val context: Context) {

    companion object {
        const val PHOTO_WIDTH = 700
        const val PHOTO_HEIGHT = 700
        const val PHOTO_QUALITY = 80
    }

    fun resizePhotoAndPrepare(file: File, finalDirPath: String): Single<File> {

        return Single.create {
            val newFile = resizePhoto(file, finalDirPath)
            if (newFile != null) {
                it.onSuccess(newFile)
            } else {
                it.onError(Exception("Can't resize file"))
            }
        }
    }

    private fun resizePhoto(file: File, finalDirPath: String): File? {

        if (!file.exists()) {
            throw IllegalArgumentException("ImageResizeUtils - file is null or not exist")
        }

        // Step 1 - The image will be scaled down before detection is run
        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(file.absolutePath, bmOptions)
        val photoW = bmOptions.outWidth
        val photoH = bmOptions.outHeight

        if (photoW > PHOTO_WIDTH || photoH > PHOTO_HEIGHT) {
            // Determine how much to scale down the image and decode the image file into a Bitmap sized
            bmOptions.inSampleSize = Math.max(photoW / PHOTO_WIDTH, photoH / PHOTO_HEIGHT)
        }
        bmOptions.inPurgeable = true
        bmOptions.inJustDecodeBounds = false
        var photo = BitmapFactory.decodeFile(file.absolutePath, bmOptions) // prepared photo

        // Step 2 - check orientation
        val orientation = getRightAngleImage(file.absolutePath)
        if (orientation > 0) {
            val matrix = Matrix()
            matrix.postRotate(orientation.toFloat())
            photo = Bitmap.createBitmap(photo, 0, 0, photo.width,
                    photo.height, matrix, true)
        }

        // Step 3 - save photo
        try {
            val newFile = getFile(finalDirPath) ?: return null
            if (newFile.exists()) {
                newFile.delete()
            }
            val fos = FileOutputStream(newFile)
            photo.compress(Bitmap.CompressFormat.JPEG, PHOTO_QUALITY, fos)
            fos.close()
            return newFile
        } catch (e: FileNotFoundException) {
            Timber.e("File not found: " + e.toString())
            return null
        } catch (e: IOException) {
            Timber.e("Error accessing file: " + e.toString())
            return null
        }
    }

    fun getFile(dirPath: String): File? {

        val root = File(dirPath)
        if (!root.exists()) {
            if (!root.mkdirs()) return null
        }
        return File(dirPath + "/" + System.currentTimeMillis() + ".jpg")
    }

    fun getRightAngleImage(photoPath: String): Int {

        try {
            val ei = ExifInterface(photoPath)
            val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

            return when (orientation) {
                ExifInterface.ORIENTATION_NORMAL -> 0
                ExifInterface.ORIENTATION_ROTATE_90 -> 90
                ExifInterface.ORIENTATION_ROTATE_180 -> 180
                ExifInterface.ORIENTATION_ROTATE_270 -> 270
                ExifInterface.ORIENTATION_UNDEFINED -> 0
                else -> 90
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0
    }
}