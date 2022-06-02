# food_api_room
build_gradle

implementation 'androidx.legacy:legacy-support-v4:1.0.0'
def nav_version = "2.4.2"
def lifecycle_version = "2.5.0-rc01"
implementation 'androidx.core:core-ktx:1.7.0'
implementation 'androidx.appcompat:appcompat:1.3.0'
implementation 'com.google.android.material:material:1.4.0'
implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
testImplementation 'junit:junit:4.13.2'
androidTestImplementation 'androidx.test.ext:junit:1.1.3'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
// Kotlin Nav_component
implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
//intuit
implementation 'com.intuit.sdp:sdp-android:1.0.6'
implementation 'com.intuit.ssp:ssp-android:1.0.6'
//gif
implementation 'pl.droidsonroids.gif:android-gif-drawable:1.2.17'
//Retrofit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
//img
implementation 'com.github.bumptech.glide:glide:4.12.0'
//ViewModel_not_work
//noinspection GradleDependency
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
