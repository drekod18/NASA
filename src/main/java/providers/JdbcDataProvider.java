package providers;

import lombok.extern.slf4j.Slf4j;
import model.database.*;

import java.sql.*;
import java.time.LocalDateTime;

@Slf4j
public record JdbcDataProvider(Connection connection) {

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO User (username, emailAddress, userRole, passwordHash, accountCreationDate, isVerified, profilePictureUrl, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmailAddress());
            ps.setString(3, user.getUserRole());
            ps.setString(4, user.getPasswordHash());
            ps.setTimestamp(5, Timestamp.valueOf(user.getAccountCreationDate()));
            ps.setBoolean(6, user.isVerified());
            ps.setString(7, user.getProfilePictureUrl());
            ps.setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
            ps.setTimestamp(9, Timestamp.valueOf(user.getUpdatedAt()));
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error creating user: {}", e.getMessage());
            throw e;
        }
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM User WHERE userId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("emailAddress"),
                        rs.getString("userRole"),
                        rs.getString("passwordHash"),
                        rs.getTimestamp("accountCreationDate").toLocalDateTime(),
                        rs.getBoolean("isVerified"),
                        rs.getString("profilePictureUrl"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            log.error("Error getting user by id: {}", e.getMessage());
            throw e;
        }
        return null;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE User SET username = ?, emailAddress = ?, userRole = ?, passwordHash = ?, accountCreationDate = ?, isVerified = ?, profilePictureUrl = ?, createdAt = ?, updatedAt = ? WHERE userId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmailAddress());
            ps.setString(3, user.getUserRole());
            ps.setString(4, user.getPasswordHash());
            ps.setTimestamp(5, Timestamp.valueOf(user.getAccountCreationDate()));
            ps.setBoolean(6, user.isVerified());
            ps.setString(7, user.getProfilePictureUrl());
            ps.setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
            ps.setTimestamp(9, Timestamp.valueOf(user.getUpdatedAt()));
            ps.setInt(10, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error updating user: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM User WHERE userId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error deleting user: {}", e.getMessage());
            throw e;
        }
    }



    public void createSpaceWeatherData(SpaceWeatherData data) throws SQLException {
        String sql = "INSERT INTO SpaceWeatherData (timestamp, solarActivityLevel, magneticFieldStrength, observationLocation, cosmicRadiation, solarWindSpeed, createdAt, updatedAt, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(data.getTimestamp().getTime()));
            ps.setInt(2, data.getSolarActivityLevel());
            ps.setInt(3, data.getMagneticFieldStrength());
            ps.setString(4, data.getObservationLocation());
            ps.setFloat(5, data.getCosmicRadiation());
            ps.setInt(6, data.getSolarWindSpeed());
            ps.setTimestamp(7, Timestamp.valueOf(data.getCreatedAt()));
            ps.setTimestamp(8, Timestamp.valueOf(data.getUpdatedAt()));
            ps.setInt(9, data.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error creating space weather data: {}", e.getMessage());
            throw e;
        }
    }

    public SpaceWeatherData getSpaceWeatherDataById(int id) throws SQLException {
        String sql = "SELECT * FROM SpaceWeatherData WHERE spaceWeatherId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SpaceWeatherData(
                        rs.getInt("spaceWeatherId"),
                        rs.getTimestamp("timestamp"),
                        rs.getInt("solarActivityLevel"),
                        rs.getInt("magneticFieldStrength"),
                        rs.getString("observationLocation"),
                        rs.getFloat("cosmicRadiation"),
                        rs.getInt("solarWindSpeed"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime(),
                        rs.getInt("userId")
                );
            }
        } catch (SQLException e) {
            log.error("Error getting space weather data by id: {}", e.getMessage());
            throw e;
        }
        return null;
    }

    public void updateSpaceWeatherData(SpaceWeatherData data) throws SQLException {
        String sql = "UPDATE SpaceWeatherData SET timestamp = ?, solarActivityLevel = ?, magneticFieldStrength = ?, observationLocation = ?, cosmicRadiation = ?, solarWindSpeed = ?, createdAt = ?, updatedAt = ?, userId = ? WHERE spaceWeatherId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(data.getTimestamp().getTime()));
            ps.setInt(2, data.getSolarActivityLevel());
            ps.setInt(3, data.getMagneticFieldStrength());
            ps.setString(4, data.getObservationLocation());
            ps.setFloat(5, data.getCosmicRadiation());
            ps.setInt(6, data.getSolarWindSpeed());
            ps.setTimestamp(7, Timestamp.valueOf(data.getCreatedAt()));
            ps.setTimestamp(8, Timestamp.valueOf(data.getUpdatedAt()));
            ps.setInt(9, data.getUserId());
            ps.setInt(10, data.getSpaceWeatherId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error updating space weather data: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteSpaceWeatherData(int id) throws SQLException {
        String sql = "DELETE FROM SpaceWeatherData WHERE spaceWeatherId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error deleting space weather data: {}", e.getMessage());
            throw e;
        }
    }


    public void createEducationalContent(EducationalContent content) throws SQLException {
        String sql = "INSERT INTO EducationalContent (contentTitle, contentType, difficultyLevel, estimatedReadingTime, isRecommended, createdAt, updatedAt, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, content.getContentTitle());
            ps.setString(2, content.getContentType());
            ps.setString(3, content.getDifficultyLevel());
            ps.setInt(4, content.getEstimatedReadingTime());
            ps.setBoolean(5, content.isRecommended());
            ps.setTimestamp(6, Timestamp.valueOf(content.getCreatedAt()));
            ps.setTimestamp(7, Timestamp.valueOf(content.getUpdatedAt()));
            ps.setInt(8, content.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error creating educational content: {}", e.getMessage());
            throw e;
        }
    }

    public EducationalContent getEducationalContentById(int id) throws SQLException {
        String sql = "SELECT * FROM EducationalContent WHERE contentId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new EducationalContent(
                        rs.getInt("contentId"),
                        rs.getString("contentTitle"),
                        rs.getString("contentType"),
                        rs.getString("difficultyLevel"),
                        rs.getInt("estimatedReadingTime"),
                        rs.getBoolean("isRecommended"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime(),
                        rs.getInt("userId")
                );
            }
        } catch (SQLException e) {
            log.error("Error getting educational content by id: {}", e.getMessage());
            throw e;
        }
        return null;
    }

    public void createObservationConditions(ObservationConditions conditions) throws SQLException {
        String sql = "INSERT INTO ObservationConditions (cloudCoveragePercentage, atmosphericSeeingQuality, observationLocation, observationTimestamp, temperature, humidity, createdAt, updatedAt, spaceWeatherId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, conditions.getCloudCoveragePercentage());
            ps.setInt(2, conditions.getAtmosphericSeeingQuality());
            ps.setString(3, conditions.getObservationLocation());
            ps.setTimestamp(4, new Timestamp(conditions.getObservationTimestamp().getTime())); // Date to Timestamp
            ps.setFloat(5, conditions.getTemperature());
            ps.setFloat(6, conditions.getHumidity());
            ps.setTimestamp(7, Timestamp.valueOf(conditions.getCreatedAt())); // LocalDateTime to Timestamp
            ps.setTimestamp(8, Timestamp.valueOf(conditions.getUpdatedAt())); // LocalDateTime to Timestamp
            ps.setInt(9, conditions.getSpaceWeatherId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error creating observation conditions", e);
        }
    }

    // READ
    public ObservationConditions getObservationConditionsById(int id) throws SQLException {
        String sql = "SELECT * FROM ObservationConditions WHERE observationId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ObservationConditions(
                        rs.getInt("observationId"),
                        rs.getInt("cloudCoveragePercentage"),
                        rs.getInt("atmosphericSeeingQuality"),
                        rs.getString("observationLocation"),
                        rs.getTimestamp("observationTimestamp"),
                        rs.getFloat("temperature"),
                        rs.getFloat("humidity"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime(),
                        rs.getInt("spaceWeatherId")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching observation conditions by ID", e);
        }
        return null;
    }

    // UPDATE
    public void updateObservationConditions(ObservationConditions conditions) throws SQLException {
        String sql = "UPDATE ObservationConditions SET cloudCoveragePercentage = ?, atmosphericSeeingQuality = ?, observationLocation = ?, observationTimestamp = ?, temperature = ?, humidity = ?, createdAt = ?, updatedAt = ?, spaceWeatherId = ? WHERE observationId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, conditions.getCloudCoveragePercentage());
            ps.setInt(2, conditions.getAtmosphericSeeingQuality());
            ps.setString(3, conditions.getObservationLocation());
            ps.setTimestamp(4, new Timestamp(conditions.getObservationTimestamp().getTime())); // Date to Timestamp
            ps.setFloat(5, conditions.getTemperature());
            ps.setFloat(6, conditions.getHumidity());
            ps.setTimestamp(7, Timestamp.valueOf(conditions.getCreatedAt())); // LocalDateTime to Timestamp
            ps.setTimestamp(8, Timestamp.valueOf(conditions.getUpdatedAt())); // LocalDateTime to Timestamp
            ps.setInt(9, conditions.getSpaceWeatherId());
            ps.setInt(10, conditions.getObservationId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error updating observation conditions", e);
        }
    }

    // DELETE
    public void deleteObservationConditions(int id) throws SQLException {
        String sql = "DELETE FROM ObservationConditions WHERE observationId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error deleting observation conditions", e);
        }
    }

    // CRUD операции для SpacePicture
    public void createSpacePicture(SpacePicture picture) throws SQLException {
        String sql = "INSERT INTO SpacePicture (imageUrl, imageDescription, captureTimestamp, photographer, location, createdAt, updatedAt, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, picture.getImageUrl());
            ps.setString(2, picture.getImageDescription());
            ps.setTimestamp(3, new Timestamp(picture.getCaptureTimestamp().getTime())); // Date to Timestamp
            ps.setString(4, picture.getPhotographer());
            ps.setString(5, picture.getLocation());
            ps.setTimestamp(6, Timestamp.valueOf(picture.getCreatedAt())); // LocalDateTime to Timestamp
            ps.setTimestamp(7, Timestamp.valueOf(picture.getUpdatedAt())); // LocalDateTime to Timestamp
            ps.setInt(8, picture.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error creating space picture", e);
        }
    }

    // READ
    public SpacePicture getSpacePictureById(int id) throws SQLException {
        String sql = "SELECT * FROM SpacePicture WHERE pictureId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SpacePicture(
                        rs.getInt("pictureId"),
                        rs.getString("imageUrl"),
                        rs.getString("imageDescription"),
                        rs.getTimestamp("captureTimestamp"),
                        rs.getString("photographer"),
                        rs.getString("location"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime(),
                        rs.getInt("userId")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching space picture by ID", e);
        }
        return null;
    }

    // UPDATE
    public void updateSpacePicture(SpacePicture picture) throws SQLException {
        String sql = "UPDATE SpacePicture SET imageUrl = ?, imageDescription = ?, captureTimestamp = ?, photographer = ?, location = ?, createdAt = ?, updatedAt = ?, userId = ? WHERE pictureId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, picture.getImageUrl());
            ps.setString(2, picture.getImageDescription());
            ps.setTimestamp(3, new Timestamp(picture.getCaptureTimestamp().getTime())); // Date to Timestamp
            ps.setString(4, picture.getPhotographer());
            ps.setString(5, picture.getLocation());
            ps.setTimestamp(6, Timestamp.valueOf(picture.getCreatedAt())); // LocalDateTime to Timestamp
            ps.setTimestamp(7, Timestamp.valueOf(picture.getUpdatedAt())); // LocalDateTime to Timestamp
            ps.setInt(8, picture.getUserId());
            ps.setInt(9, picture.getPictureId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error updating space picture", e);
        }
    }

    // DELETE
    public void deleteSpacePicture(int id) throws SQLException {
        String sql = "DELETE FROM SpacePicture WHERE pictureId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error deleting space picture", e);
        }
    }
}
