/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Using DataBindingUtil to set layout id activity_main to ActivityMainBinding
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // DRAWERLAYOUT
        // 1. Initialize the drawerLayout from the binding variable
        drawerLayout = binding.drawerLayout

        // UP BUTTON
        // 1. Find the navController from myNavHostFragment. Since we're using KTX, you can call this.findNavController
        val navController = this.findNavController(R.id.myNavHostFragment)
        // 2. Link the navController to our ActionBar by calling NavigationUI.setupActionBarWithNavController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // DRAWERLAYOUT
        // 2. Create an appBarConfiguration with the navController.graph and drawerLayout
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        // DRAWERLAYOUT
        // 3. Hook up the navigation UI up to the navigation view
        NavigationUI.setupWithNavController(binding.navView, navController)

        // 5. Prevent drawerLayout usage from other screens than TitleFragment (startDestination)
        // Use of AddOnDestinationChangedListener
        navController.addOnDestinationChangedListener { nc, nd, args ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // DRAWERLAYOUT
        // 3. Find the navController
        val navController = this.findNavController(R.id.myNavHostFragment)
        // 4. return NavigationUI.navigateUp
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
