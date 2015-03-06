# SmokeSignals#
CS56 Winter 2015 - Independent Android Project

Original Author: [Pedro M. Sosa](http://www.konukoii.com)

<h2>Project Objective</h2>
Android app that allows you to automate several tasks that can be triggered by an incoming SMS

<h2>Implemented Methods</h2>
Say you forgot your phone, Using someone else's phone you can text yourself:
- **"//Location" :**  and the phone will text you back with it's gps coordinates.
- **"//Contact [name]" :** and the phone will text you back with the stored contact info of anyone who fully or partially matches [name].
- **"//Battery" :** and the phone will text you back with it's battery percentage.
- **"//Calls" :** and the phone will text you back the last phone calls it has gotten.
- **"//Ring" : (WIP)** and the phone will start ringing.
- **"//Help" :** Return the list of all possible 

<h2>Ideas for Improvments</h2>

Essentially you can come up with tons of different things to automate, so you can use that phone "by proxy" through SMS from some other phone.

- **Custom Commands:** Commands shouldn't be hardcoded. The user should be allowed to change them.
- **Fix Ringing:** It should ring for say 2 minutes and then stop ringing as soon as the user interacts with the phone *(hint: you might find Intent android.intent.action.USER_PRESENT usefull)*
- **GUI :**Setup a GUI that lets you change commands to custom commands, blacklist, whitelists, turn certain commands on or off.
- **Whitlist/Blacklist:** which phones are allowed to use our service.
- **//Location:** Use Fused location instead of GSM only. Maybe add some timer, so that if it can't find any GSM location it stops after certain time (otherwise it'll drain the battery)
- **(NEW) //Powersave:** Turn off/on Wi-Fi or Mobile Data
- **(NEW) //Snap:** Take a selfie and send media sms with the picture (mobile data has to be enabled)
- **(NEW) //ScreenSnap:** Take a screenshot of what is going on your phone currently and send media sms with the picture
- **//Battery:** Add info regarding if charging/discharging status
- **//SMS [number] m:[message] :** Send an sms to [number] saying [message].
