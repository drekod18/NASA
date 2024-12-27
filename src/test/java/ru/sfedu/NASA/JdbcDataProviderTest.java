package ru.sfedu.NASA;

import lombok.extern.slf4j.Slf4j;
import model.database.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import providers.JdbcDataProvider;
import utils.PostgresUtil;

import java.sql.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Slf4j
public class JdbcDataProviderTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    private JdbcDataProvider dataProvider;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        dataProvider = new JdbcDataProvider(mockConnection);
    }

    @Test
    public void testCreateUser() throws SQLException {
        User user = new User(
                1,
                "JohnDoe",
                "john.doe@example.com",
                "admin",
                "hashedpassword",
                LocalDateTime.now(),
                true,
                "profilepic.jpg",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        dataProvider.createUser(user);

        verify(mockStatement, times(1)).setString(1, user.getUsername());
        verify(mockStatement, times(1)).setString(2, user.getEmailAddress());
        verify(mockStatement, times(1)).setString(3, user.getUserRole());
        verify(mockStatement, times(1)).setString(4, user.getPasswordHash());
        verify(mockStatement, times(1)).setTimestamp(5, Timestamp.valueOf(user.getAccountCreationDate()));
        verify(mockStatement, times(1)).setBoolean(6, user.isVerified());
        verify(mockStatement, times(1)).setString(7, user.getProfilePictureUrl());
        verify(mockStatement, times(1)).setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
        verify(mockStatement, times(1)).setTimestamp(9, Timestamp.valueOf(user.getUpdatedAt()));
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetUserById() throws SQLException {
        int userId = 1;
        User expectedUser = new User(
                userId,
                "JohnDoe",
                "john.doe@example.com",
                "admin",
                "hashedpassword",
                LocalDateTime.now(),
                true,
                "profilepic.jpg",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("userId")).thenReturn(userId);
        when(mockResultSet.getString("username")).thenReturn(expectedUser.getUsername());
        when(mockResultSet.getString("emailAddress")).thenReturn(expectedUser.getEmailAddress());
        when(mockResultSet.getString("userRole")).thenReturn(expectedUser.getUserRole());
        when(mockResultSet.getString("passwordHash")).thenReturn(expectedUser.getPasswordHash());
        when(mockResultSet.getTimestamp("accountCreationDate")).thenReturn(Timestamp.valueOf(expectedUser.getAccountCreationDate()));
        when(mockResultSet.getBoolean("isVerified")).thenReturn(expectedUser.isVerified());
        when(mockResultSet.getString("profilePictureUrl")).thenReturn(expectedUser.getProfilePictureUrl());
        when(mockResultSet.getTimestamp("createdAt")).thenReturn(Timestamp.valueOf(expectedUser.getCreatedAt()));
        when(mockResultSet.getTimestamp("updatedAt")).thenReturn(Timestamp.valueOf(expectedUser.getUpdatedAt()));

        User result = dataProvider.getUserById(userId);

        assertNotNull(result);
        assertEquals(expectedUser.getUsername(), result.getUsername());
        assertEquals(expectedUser.getEmailAddress(), result.getEmailAddress());
    }

    @Test
    public void testUpdateUser() throws SQLException {
        User user = new User(
                1,
                "JohnDoe",
                "john.doe@example.com",
                "admin",
                "newpasswordhash",
                LocalDateTime.now(),
                true,
                "newprofilepic.jpg",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        dataProvider.updateUser(user);

        verify(mockStatement, times(1)).setString(1, user.getUsername());
        verify(mockStatement, times(1)).setString(2, user.getEmailAddress());
        verify(mockStatement, times(1)).setString(3, user.getUserRole());
        verify(mockStatement, times(1)).setString(4, user.getPasswordHash());
        verify(mockStatement, times(1)).setTimestamp(5, Timestamp.valueOf(user.getAccountCreationDate()));
        verify(mockStatement, times(1)).setBoolean(6, user.isVerified());
        verify(mockStatement, times(1)).setString(7, user.getProfilePictureUrl());
        verify(mockStatement, times(1)).setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
        verify(mockStatement, times(1)).setTimestamp(9, Timestamp.valueOf(user.getUpdatedAt()));
        verify(mockStatement, times(1)).setInt(10, user.getUserId());
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteUser() throws SQLException {
        int userId = 1;

        dataProvider.deleteUser(userId);

        verify(mockStatement, times(1)).setInt(1, userId);
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    public void testCreateSpaceWeatherData() throws SQLException {
        SpaceWeatherData data = new SpaceWeatherData(
                1,
                new java.util.Date(),
                5,
                100,
                "Location A",
                0.7f,
                350,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1
        );

        dataProvider.createSpaceWeatherData(data);

        verify(mockStatement, times(1)).setTimestamp(1, new Timestamp(data.getTimestamp().getTime()));
        verify(mockStatement, times(1)).setInt(2, data.getSolarActivityLevel());
        verify(mockStatement, times(1)).setInt(3, data.getMagneticFieldStrength());
        verify(mockStatement, times(1)).setString(4, data.getObservationLocation());
        verify(mockStatement, times(1)).setFloat(5, data.getCosmicRadiation());
        verify(mockStatement, times(1)).setInt(6, data.getSolarWindSpeed());
        verify(mockStatement, times(1)).setTimestamp(7, Timestamp.valueOf(data.getCreatedAt()));
        verify(mockStatement, times(1)).setTimestamp(8, Timestamp.valueOf(data.getUpdatedAt()));
        verify(mockStatement, times(1)).setInt(9, data.getUserId());
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetSpaceWeatherDataById() throws SQLException {
        int spaceWeatherId = 1;
        SpaceWeatherData expectedData = new SpaceWeatherData(
                spaceWeatherId,
                new java.util.Date(),
                5,
                100,
                "Location A",
                0.7f,
                350,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1
        );

        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("spaceWeatherId")).thenReturn(spaceWeatherId);
        when(mockResultSet.getTimestamp("timestamp")).thenReturn(new Timestamp(expectedData.getTimestamp().getTime()));
        when(mockResultSet.getInt("solarActivityLevel")).thenReturn(expectedData.getSolarActivityLevel());
        when(mockResultSet.getInt("magneticFieldStrength")).thenReturn(expectedData.getMagneticFieldStrength());
        when(mockResultSet.getString("observationLocation")).thenReturn(expectedData.getObservationLocation());
        when(mockResultSet.getFloat("cosmicRadiation")).thenReturn(expectedData.getCosmicRadiation());
        when(mockResultSet.getInt("solarWindSpeed")).thenReturn(expectedData.getSolarWindSpeed());
        when(mockResultSet.getTimestamp("createdAt")).thenReturn(Timestamp.valueOf(expectedData.getCreatedAt()));
        when(mockResultSet.getTimestamp("updatedAt")).thenReturn(Timestamp.valueOf(expectedData.getUpdatedAt()));
        when(mockResultSet.getInt("userId")).thenReturn(expectedData.getUserId());

        SpaceWeatherData result = dataProvider.getSpaceWeatherDataById(spaceWeatherId);

        assertNotNull(result);
        assertEquals(expectedData.getSolarActivityLevel(), result.getSolarActivityLevel());
        assertEquals(expectedData.getObservationLocation(), result.getObservationLocation());
    }

    @AfterEach
    public void tearDown() {
        // Cleanup after each test
    }
}
