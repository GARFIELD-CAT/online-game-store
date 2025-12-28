import { applyMiddleware, createStore } from "redux";
import thunk from "redux-thunk";
import { gameReducer } from "./gameReducer";

export const store = createStore(gameReducer, applyMiddleware(thunk));
