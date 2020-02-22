package com.example.mvrxexample.robot

import android.app.Activity
import android.content.ComponentName
import android.util.TypedValue
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasBackground
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.CoreMatchers.not
import kotlin.math.roundToInt

open class BaseRobot {

    fun fillEditText(resId: Int, text: String): ViewInteraction =
        onView(withId(resId)).perform(
            ViewActions.replaceText(text),
            ViewActions.closeSoftKeyboard()
        )

    fun clickButton(resId: Int): ViewInteraction =
        onView((withId(resId))).perform(ViewActions.click())

    fun clickButton(name: String): ViewInteraction =
        onView((withText(name))).perform(ViewActions.click())

    fun textView(resId: Int): ViewInteraction = onView(withId(resId))

    fun matchText(viewInteraction: ViewInteraction, text: String): ViewInteraction = viewInteraction
        .check(matches(withText(text)))

    fun matchText(resId: Int, text: String): ViewInteraction = matchText(textView(resId), text)

    fun matchResources(@IdRes viewId: Int, @DrawableRes resId: Int) =
        assertHasDrawable(viewId, resId)

    fun clickListItem(listRes: Int, position: Int) {
        onData(anything())
            .inAdapterView(allOf(withId(listRes)))
            .atPosition(position).perform(ViewActions.click())
    }

    fun closeKeyboard(): ViewAction = ViewActions.closeSoftKeyboard()

    fun Fragment.isKeyboardShown(): Boolean {
        val visibleBounds = android.graphics.Rect()
        val rootView = requireActivity().findViewById<View>(android.R.id.content)
        rootView.getWindowVisibleDisplayFrame(visibleBounds)
        val heightDiff = rootView.height - visibleBounds.height()
        val marginOfError = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics
        ).roundToInt()
        return heightDiff > marginOfError
    }

    fun <T> isActivityLaunched(thisActivity: Activity, launchedActivity: Class<T>) =
        intended(hasComponent(ComponentName(thisActivity, launchedActivity)))

    fun matchBackground(viewId: Int, @DrawableRes backgroundResId: Int) =
        onView(withId(viewId)).check(matches(hasBackground(backgroundResId)))

    fun isDisplayed(resId: Int): ViewInteraction =
        onView(withId(resId)).check(matches(isDisplayed()))

    fun isNotDisplayed(resId: Int): ViewInteraction =
        onView(withId(resId)).check(matches(not(isDisplayed())))

    fun isCompletelyDisplayed(resId: Int): ViewInteraction =
        onView(withId(resId)).check(matches(isCompletelyDisplayed()))

    fun isDisplayed(name: String): ViewInteraction =
        onView(withText(name)).check(matches(isDisplayed()))

    fun isDisplayed(@IdRes resId: Int, name: String): ViewInteraction =
        onView(allOf(withId(resId), withText(name))).check(matches(isDisplayed()))

    fun isGone(@IdRes resId: Int): ViewInteraction =
        onView(withId(resId)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

    fun isGone(@IdRes resId: Int, name: String): ViewInteraction =
        onView(allOf(withText(name), withId(resId)))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

    fun doesNotExists(resId: Int): ViewInteraction = onView(withId(resId)).check(doesNotExist())

    fun doesNotExists(name: String): ViewInteraction = onView(withText(name)).check(doesNotExist())

    fun doesNotExists(@IdRes resId: Int, name: String): ViewInteraction =
        onView(allOf(withId(resId), withText(name))).check(doesNotExist())

    fun isNotDisplayed(@IdRes resId: Int, name: String): ViewInteraction =
        onView(allOf(withText(name), withId(resId)))
            .check(matches(not(isDisplayed())))

    fun isVisible(@IdRes resId: Int): Boolean =
        try {
            onView(withId(resId)).check(matches(isDisplayed()))
            true
        } catch (e: NoMatchingViewException) {
            false
        }

    fun isVisible(name: String, @IdRes resId: Int): Boolean =
        try {
            onView(
                allOf(withText(name), withId(resId))
            ).check(matches(isDisplayed()))
            true
        } catch (e: NoMatchingViewException) {
            false
        }

    fun getResourceString(id: Int): String {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        return targetContext.getString(id)
    }

//    fun waitFor(millis: Long): ViewInteraction =
//        onView(ViewMatchers.isRoot()).perform(WaitViewAction(millis))

    fun swipeUpList(resId: Int): ViewInteraction = onView(withId(resId)).perform(swipeUp())

    fun swipeUpListFromCenter(resId: Int): ViewInteraction = onView(withId(resId)).perform(
        ViewActions.actionWithAssertions(
            GeneralSwipeAction(
                Swipe.FAST,
                GeneralLocation.CENTER,
                GeneralLocation.TOP_CENTER,
                Press.FINGER
            )
        )
    )

//    fun performClickClickableSpan(
//        viewInteraction: ViewInteraction,
//        textToClick: String
//    ): ViewInteraction =
//        viewInteraction.perform(ClickClickableSpanViewAction(textToClick))
}