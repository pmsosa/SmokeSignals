# SmokeSignals

Pedro M. Sosa

Android app that allows you to automate several tasks that can be triggered by an incoming SMS

Say you forgot your phone, Using someone else's phone you can text yourself:
"//Location?"  and the phone will text you back with it's gps coordinates.
"//Contact search: Steve" and the phone will text you back with the contact info of any "Steve"s that it has stored.
"//Battery?" and the phone will text you back with it's battery percentage.
"//Ring!" and the phone will start ringing.
"//Calls?" and the phone will text you back the last phone calls it has gotten.

----------(Disclaimer: Some of these are ideas that may or may not get implemented)----------

Essentially you can come up with tons of different things to automate, so you can use that phone "by proxy" through SMS from some other phone.

From a CS56 perspective: The nice thing about this project is how it can keep expanding to add more features, which might be interesting for further classes.

----Some technicall stuff------
This is essentially an Android service that implements a BroadCast Reciever (that takes IncomingSMS broadcasts).
The app also should a simple interface where the user can change some settings, such as: The sms messages it should awnser to, etc.
