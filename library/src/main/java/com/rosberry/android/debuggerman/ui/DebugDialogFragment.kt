/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.ui

import android.app.Dialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.rosberry.abstractrecycler.AbstractItem
import com.rosberry.android.debuggerman.BuildConfig
import com.rosberry.android.debuggerman.R
import com.rosberry.android.debuggerman.presentation.Debug
import com.rosberry.android.debuggerman.presentation.DebugPresenter
import com.rosberry.android.debuggerman.presentation.DebugView
import kotlinx.android.synthetic.main.df_debug.*
import kotlinx.android.synthetic.main.df_debug.view.*

/**
 * @author Alexei Korshun on 2019-10-20.
 */
class DebugDialogFragment : DialogFragment(), DebugView {

    companion object {

        var supportedItems: List<Debug> = listOf()

        private const val TAG = "DebugDialogFragment"
        private const val NOTIFICATION_ID = 100
        private const val CHANNEL_ID = "debug dialog"
        private const val ACTION_OPEN = BuildConfig.LIBRARY_PACKAGE_NAME + ".open_debug"

        fun showNotification(context: Context, clazz: Class<*>) {
            val nm = NotificationManagerCompat.from(context)
            val intent = Intent(context, clazz).apply {
                action = ACTION_OPEN
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            val contentIntent = PendingIntent.getActivity(context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT)

            createNotificationChannel(nm)

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Debug")
                .setContentText("Click to open debug dialog")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(NotificationCompat.VISIBILITY_SECRET)
                .setSmallIcon(R.drawable.ic_bug_report_black_24dp)
                .setContentIntent(contentIntent)
                .build()

            nm.notify(NOTIFICATION_ID, notification)
        }

        fun onNewIntent(i: Intent?, supportFragmentManager: FragmentManager) {
            if (i != null && i.action == ACTION_OPEN) newInstance().show(supportFragmentManager, TAG)

        }

        private fun newInstance(): DebugDialogFragment {
            return DebugDialogFragment()
        }

        private fun createNotificationChannel(nm: NotificationManagerCompat) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Debug screen"
                val description = "Debug app"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(CHANNEL_ID, name, importance)

                channel.description = description

                nm.createNotificationChannel(channel)
            }
        }
    }

    private val presenter: DebugPresenter = DebugPresenter()

    private val adapter: DebugAdapter by lazy { DebugAdapter(emptyList()) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_TITLE, R.style.DebugDialogStyle)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.df_debug, parent, false)
            .apply {
                this.buttonApply.setOnClickListener { presenter.clickApply() }
                this.buttonCancel.setOnClickListener { presenter.clickDismiss() }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerDebug.setHasFixedSize(true)
        recyclerDebug.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        recyclerDebug.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.onAttach(this)
    }

    override fun onPause() {
        presenter.onDetach()
        super.onPause()
    }

    override fun setDebugItems(items: List<AbstractItem>) {
        adapter.showItems(items)
    }

}