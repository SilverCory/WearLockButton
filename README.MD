Wear Lock Button
====

**Android WearOS lock button**

---

I have a TicWatch C2, and there is no method of easily locking the screen.
There is one multi-function button which I have configured to bring up Google Pay.

This app will allow easy locking without stifling the existing functionality.
Using a multi-function button by holding said button for over 500 milliseconds will lock the screen.

---

This is slightly hacky in that it uses an accessibility service to detect the key press.
Using the accessibility service isn't ideal since it poses a few trust/security issues due to the information
that is exposed using the accessibility API.

There is no current method of detecting key press events in the background without an accessibility.

---

TODO:
- [x] Allow configuration of which multi-function button to use.
- [x] Allow configuration of min and max button press time.
- [ ] **Pretty** permission page. Looks like PreferenceFragmentCompat doesn't play well with WearOS?
- [ ] Meme icon.