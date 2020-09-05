package com.alphaone.pos.constants

class Constant {
    interface ServerEndpoint {
        companion object {
            const val DO_LOGIN = "account/login"
            const val FETCH_DASHBOARD_DATA = "account/"
           // const val DO_LOGIN = "api/users?page=2"
        }
    }

    interface SharedPreferences {
        companion object {
            const val LOGGED_IN_USER = "LOGGED_IN_USER"
            const val IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN"
        }
    }

    interface Common {
        companion object {
            const val SHARED_PREFERENCES = "SHARED_PREFERENCES"
        }
    }

    interface IntentExtras {
        companion object {
            const val NAV_VIEW_ID = "NAV_VIEW_ID"
        }
    }

    interface AuthorizationHeader {
        companion object {
            const val REST_HEADER = "User-Auth" //String REST_HEADER = "Authorization";
        }
    }

    interface GoogleApi {
        companion object {
            const val GOOGLE_SERVER_API_KEY = "AIzaSyCyR6g7k2mjvroLzL0d8rDucO8qCy-8Lko"
        }
    }
}