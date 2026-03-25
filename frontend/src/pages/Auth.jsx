import { useState } from "react";
import SignIn from "../components/Authentication/SignIn";
import SignUp from "../components/Authentication/SignUp";

function Auth() {
  const [authSwitch, setAuthSwitch] = useState(true);
  return (
    <div className="min-h-screen bg-gray-100 p-2 flex items-center justify-center">
      {authSwitch ? (
        <SignIn authSwitch={authSwitch} setAuthSwitch={setAuthSwitch} />
      ) : (
        <SignUp authSwitch={authSwitch} setAuthSwitch={setAuthSwitch} />
      )}
    </div>
  );
}

export default Auth;