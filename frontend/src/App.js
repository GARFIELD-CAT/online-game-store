import "./App.css";
import { Route, Routes, Navigate } from "react-router-dom";
import MainPage from "./pages/MainPage";
import OrderPage from "./pages/OrderPage";
import LoginPage from "./pages/LoginPage";
import { routes } from "./utils/routes";
import OrderListPage from "./pages/OrderListPage";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route exact path={routes.LOGIN_PAGE} element={<LoginPage />} />
        <Route exact path={routes.MAIN_PAGE} element={<MainPage />} />
        <Route path={routes.ORDER_PAGE} element={<OrderPage />} />
        <Route path={routes.ORDER_LIST_PAGE} element={<OrderListPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </div>
  );
}

export default App;
