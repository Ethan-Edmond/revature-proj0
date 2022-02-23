package com.ethanedmond.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionSingletonTest {

    @Test
    void getConn() {
        try {
            Connection conn = ConnectionSingleton.getConn();
            assertTrue(conn.isValid(10));
        } catch (SQLException err) {
            err.printStackTrace();
            assertTrue(false);
        }
    }
}