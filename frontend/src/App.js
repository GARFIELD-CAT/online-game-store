import "./App.css";
import { Route, Routes, Navigate } from "react-router-dom";
import MainPage from "./pages/MainPage";
import OrderPage from "./pages/OrderPage";
import LoginPage from "./pages/LoginPage";
import { routes } from "./utils/routes";
import OrderListPage from "./pages/OrderListPage";
import { AuthProvider, useAuth } from "./utils/AuthContext";

const ProtectedRoute = ({ children }) => {
  const { isAuthenticated } = useAuth();

  console.log(isAuthenticated);

  return isAuthenticated ? (
    children
  ) : (
    <Navigate to={routes.LOGIN_PAGE} replace />
  );
};

function App() {
  return (
    <AuthProvider value={{ isAuthenticated: true }}>
      <div className="App">
        <Routes>
          <Route exact path={routes.LOGIN_PAGE} element={<LoginPage />} />
          <Route
            exact
            path={routes.MAIN_PAGE}
            element={
              <ProtectedRoute>
                <MainPage />
              </ProtectedRoute>
            }
          />
          <Route
            path={routes.ORDER_PAGE}
            element={
              <ProtectedRoute>
                <OrderPage />
              </ProtectedRoute>
            }
          />
          <Route
            path={routes.ORDER_LIST_PAGE}
            element={
              <ProtectedRoute>
                <OrderListPage />
              </ProtectedRoute>
            }
          />
          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </div>
    </AuthProvider>
  );
}

export default App;
