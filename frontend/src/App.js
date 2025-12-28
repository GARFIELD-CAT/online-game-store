import "./App.css";
import { Route, Routes, Navigate } from "react-router-dom";
import MainPage from "./pages/MainPage";
import OrderPage from "./pages/OrderPage";
import LoginPage from "./pages/LoginPage";
import { routes } from "./utils/routes";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route exact path={routes.LOGIN_PAGE} element={<LoginPage />} />
        <Route exact path={routes.MAIN_PAGE} element={<MainPage />} />
        <Route path={routes.ORDER_PAGE} element={<OrderPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </div>
  );
}

export default App;
