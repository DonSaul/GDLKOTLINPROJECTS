### Note-Taking App

---

#### Description:
The Note-Taking App is a simple application designed to help users create and manage their notes efficiently. Users can organize their notes into different categories, add titles, content, and mark them as completed when necessary. This README provides an overview of the app's functionality, setup instructions, and dependencies.

---

#### Database Design:

The database design of the Note-Taking App consists of three main entities: Users, Notes, and Categories.

1. **Users:**
   - Each user has a unique email address and an optional username.
   - The password is stored securely using bcrypt encryption.
   - Users can have multiple categories and notes associated with them.
   - The `isActive` field determines whether the user's account is active.

2. **Notes:**
   - Each note has a title, content, and status (active or completed).
   - Notes belong to a specific user and can be organized under different categories.
   - The `isCompleted` field indicates whether the note has been marked as completed.

3. **Categories:**
   - Categories are used to organize notes.
   - Each category has a title and can contain multiple notes.
   - Categories are associated with a specific user.
   - The `icon` field is optional and allows users to add visual identifiers to their categories.

---

#### Setup Instructions:

1. **Clone Repository:**
   ```
   git clone <repository-url>
   ```
2. **Install Dependencies:**
   ```
   npm install
   ```
3. **Set Environment Variables:**
   Create a `.env` file in the root directory and add the following variables:
   ```
   PORT=3000
   DATABASE_URL=sqlite://./database.db
   JWT_SECRET=<your-secret-key>
   ```
4. **Run Database Initialization:**
   Modify the `index.js` file to invoke the `initDatabase` function from the `database.js` file, or run the following command:
   ```
   node database.js
   ```
5. **Start the Server:**
   ```
   npm start
   ```
6. **Access the Application:**
   Open a web browser and go to `http://localhost:3000` to access the Note-Taking App.

---

#### Dependencies:

- bcrypt: ^5.1.1
- cors: ^2.8.5
- dotenv: ^16.4.5
- express: ^4.19.2
- express-joi-validation: ^5.0.1
- joi: ^17.12.3
- jsonwebtoken: ^9.0.2
- passport: ^0.7.0
- passport-jwt: ^4.0.1
- sequelize: ^6.37.3
- sqlite3: ^5.1.7

---

#### Author:
- **emilioenlaluna**

---

#### License:
MIT License

---

#### Note:
Please make sure to replace `<repository-url>` and `<your-secret-key>` with your actual repository URL and secret key respectively.



