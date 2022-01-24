# Virtual Doctor Consultation

<!-- ANCHOR: introduction -->
Current status: **ALPHA**

<!-- ANCHOR_END: introduction -->

## Overview
This project utilizes Android studio, Google Cloud Platform and Firebase to create an
andoid application and use Google cloud services as backend.
This is a highly customizable app which can be altered for all virtual appointment booking
kind of application.

### Features

- [x] Google, email and phone sign up and login page for doctor and patient using Firebase authentication
- [x] Use Google calendar API for scheduling requirements
- [x] Integrate with Twillio to place phone call between the parties during the appointment time, this also performed user phone number anonymization
- [x] Backend database using Firestore where the user information and the call details are stored in 256-bit Advanced Encryption Standard.
- [x] Created Firebase cloud functions, a serverless backend framework to trigger on changes in information in the Firestore database
- [x] Integrated with Google Pay for payment options
- [ ] Group call functionality
- [ ] Ability to send messages/images/videos between patient and doctor
