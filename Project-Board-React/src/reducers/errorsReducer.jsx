import { GET_ERRORS } from "../actions/types"

const initialState = {
    error: "에러"
}

export default function(state = initialState, actions) {
    switch(actions.type) {
        case GET_ERRORS:
            return actions.payload;

        default:
            return state;
    }
}