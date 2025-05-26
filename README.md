# 🩺 Real-Time Medical Monitoring System (Java RMI)

A distributed medical system built using Java RMI, enabling doctors to monitor patient data in real-time through client-server communication. The system includes separate GUI interfaces for doctors and patients, alerting the doctor when critical health values are detected.

---

## 💡 Features

- 🔐 Login system for both clients (patients) and doctors
- 📡 Real-time communication using Java RMI
- ⚠️ Alerts for abnormal vital signs:
  - Temperature
  - Heart rate
  - Blood pressure
- 🧑‍⚕️ Doctor GUI with patient monitoring
- 👤 Client GUI for sending health data
- 🗃️ MySQL database integration for user accounts and data

---

## 🛠️ Tech Stack

- Java 17
- Java RMI (Remote Method Invocation)
- Swing (GUI)
- MySQL (with `medbd.sql`)
- JDBC

## 🚀 How to Run

1. Import the project in an IDE (e.g., IntelliJ or Eclipse)
2. Set up the MySQL database using `medbd.sql`
3. Run the `Implserv.java` to start the RMI server
4. Run `Client.java` or `Doctor.java` to connect

## 🧠 Author

- 💻 Developed by: Imane Sebbar
- 🏫 University: Ibn Tofail
- 📅 Date: 2025


