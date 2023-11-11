package com.hackathoon.datavisualizer;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.NONE;

@NoArgsConstructor(access = NONE)
public class Mappings {

    public static final String API = "/api";
    public static final String AUTH = API + "/auth";
    public static final String LOGIN = "/login";
    public static final String SIGN_IN = "/signIn";
    public static final String SIGN_UP = "/signUp";
    public static final String PUBLIC_API = API + "/public";
    public static final String DOMAINS = PUBLIC_API + "/domains";
    public static final String SKILLS = API + "/skills";
    public static final String PROFILE = API + "/profile";

}
