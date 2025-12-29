import Cookies from "js-cookie";
import Api from "../utils/Api";
import {
  getTokenAction,
  getGamesAction,
  getCurrentGameAction,
  getOrderAction,
  setIsLoadingAction,
  setError,
} from "./gameReducer";
import { setExpiresTime } from "../utils/cookiesExpiresTime";

export const loginUser = (dispatch, email, password) => {
  dispatch(setIsLoadingAction(true));
  dispatch(setError(false));

  Api.loginUser(email, password)
    .then((res) => {
      dispatch(getTokenAction(res.token));
      localStorage.setItem("jwtToken", res.token);
    })
    .then(() => {
      dispatch(setIsLoadingAction(false));
    })
    .catch((err) => {
      dispatch(setError(true));
      console.error(err);
      dispatch(setIsLoadingAction(false));
    });
};

export const getGames = (dispatch, platform, genre, sort, controller) => {
  dispatch(setIsLoadingAction(true));
  dispatch(setError(false));

  Api.getGames(platform, genre, sort, controller)
    .then((res) => {
      dispatch(getGamesAction([...res]));
    })
    .then(() => {
      dispatch(setIsLoadingAction(false));
    })
    .catch((err) => {
      if (err.name === "AbortError") {
        dispatch(setError(false));
        dispatch(setIsLoadingAction(false));
      } else {
        dispatch(setError(true));
        console.error(err);
        dispatch(setIsLoadingAction(false));
      }
    });
};

export const createOrder = (dispatch, data) => {
  dispatch(setIsLoadingAction(true));

  Api.createOrder(data)
    .then((order) => {
      dispatch(getOrderAction(order));
    })
    .then(() => {
      dispatch(setIsLoadingAction(false));
    })
    .catch((err) => {
      dispatch(setError(true));
      console.error(err);
      dispatch(setIsLoadingAction(false));
    });
};

export const createPayment = (dispatch, data) => {
  dispatch(setIsLoadingAction(true));
  Api.createPayment(data)
    .then(() => {
      dispatch(setIsLoadingAction(false));
    })
    .catch((err) => {
      dispatch(setError(true));
      console.error(err);
      dispatch(setIsLoadingAction(false));
    });
};

export const getOrder = (dispatch, id) => {
  dispatch(setIsLoadingAction(true));
  Api.getOrder(id)
    .then((order) => {
      dispatch(getOrderAction(order));
    })
    .then(() => {
      dispatch(setIsLoadingAction(false));
    })
    .catch((err) => {
      dispatch(setError(true));
      console.error(err);
      dispatch(setIsLoadingAction(false));
    });
};

export const logoutUser = (dispatch) => {
  dispatch(setIsLoadingAction(true));
  dispatch(setError(false));

  Api.logoutUser()
    .then(() => {
      dispatch(setIsLoadingAction(false));
    })
    .catch((err) => {
      dispatch(setError(true));
      console.error(err);
      dispatch(setIsLoadingAction(false));
    });
};
