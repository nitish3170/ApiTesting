package org.example;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SpotifyTest {
    String token="BQD7sccWtajSXDq0nXsx8KlsiyApfNW0vfzQ5aSstS4J0HQgPUFLjqr3tjfjBDA42LiPMO-Jr_vlJ1fkGD0AXo65F889saMNUdIBz_ngx71FfK9K_2uoPHoVc90TaUOWhOVKU87XdYRPdKGXxMuMAB7hs-89ej3vVb0sy2A6YHKPYd4YnCIEFGphbc0n45PoaUDU2GXjJt2wrYUHdYchGrORga14-1L3x_9uviHXYVZ65eMctQLz1MAo--aKVKxgBy2Y5PZVQlhV3rh5H3F2zrR3VdTUPSKYUMSgSgnM1tLRoZsE4lZTzPPrvT8b_fZR9Q1KU1c2XHDuxG6uHlqP";
    String userID=null;

    @Test(priority = 1)
    public void getCurrentUserProfile(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me");

        response.prettyPrint();
        userID=response.path("id");
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getUserTopItems(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/top/artists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test(priority = 2)
    public void getUserProfile(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/users/"+userID);
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void followPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void unfollowPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .delete("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getFollowedArtist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/following?type=artist&after=6VuMaDnrHyPL1p4EHjYLi7");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void followArtistOrUsers(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"6VuMaDnrHyPL1p4EHjYLi7\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/following?type=artist&ids=6VuMaDnrHyPL1p4EHjYLi7");
        response.prettyPrint();
        response.then().statusCode(204);
        Assert.assertEquals(response.statusCode(),204);
    }

    @Test
    public void unfollowArtistOrUsers(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"1mYsTxnqsietFxj1OgoGbG\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/me/following?type=artist&ids=1mYsTxnqsietFxj1OgoGbG");
        response.prettyPrint();
        response.then().statusCode(204);
        Assert.assertEquals(response.statusCode(),204);
    }
    @Test
    public void checkIfUserFollowsArtistOrUsers(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/following/contains?type=artist&ids=2CIMQHirSU0MQqyYHq0eOx");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void checkIfCurrentUserFollowsPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers/contains");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/tracks/11dFghVXANMlKmJXsNCbNl");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getSeveralTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/tracks?ids=7ouMYWpwJ422jRcDASZB7P");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getUserSavedTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void removeUserSavedTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"7ouMYWpwJ422jRcDASZB7P\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/me/tracks?ids=7ouMYWpwJ422jRcDASZB7P");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void checkUserSavedTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks/contains?ids=7ouMYWpwJ422jRcDASZB7P");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void getSeveralTracksAudioFeatures(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/audio-features?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void getTracksAudioFeatures(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/audio-features/11dFghVXANMlKmJXsNCbNl");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getTracksAudioAnalysis(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/audio-analysis/11dFghVXANMlKmJXsNCbNl");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getRecomendations(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/recommendations?seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical%2Ccountry&seed_tracks=0c6xIDDpzE81m2q797ordA");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

//    .......................................................................

    @Test
    public void getShow(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ");
        response.prettyPrint();
        response.then().statusCode(404);
        Assert.assertEquals(response.statusCode(),404);
    }
    @Test
    public void getSeveralshows(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/shows?ids=5CfCWKI5pZ28U0uOzXkDHe%2C5as3aKmN2k11yfDDDSrvaZ");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getShowEpisodes(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/shows/1Ev7VeqtZZiedzzS6QieDb/episodes");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getUsersSavedShow(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/shows");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void saveShowsForCurrentUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .put("https://api.spotify.com/v1/me/shows?ids=1Ev7VeqtZZiedzzS6QieDb");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void removeUserssavedShows(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .delete("https://api.spotify.com/v1/me/shows?ids=1Ev7VeqtZZiedzzS6QieDb");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void checkUserssavedShows(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/shows/contains?ids=1Ev7VeqtZZiedzzS6QieDb");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }


//    ---------------------------------------------------------------------------------

    @Test
    public void searchForItem(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/search?q=remaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis&type=album");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

//    ---------------------------------------------------------------------------------

    @Test
    public void getPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void changePlaylistDetails(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"name\": \"new Songs\",\n" +
                        "    \"description\": \"changed name\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/125h9dM54W6SxJWPAwQMZD");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getPlaylistItems(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void updatePlaylistItems(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"range_start\": 1,\n" +
                        "    \"insert_before\": 3,\n" +
                        "    \"range_length\": 2\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/125h9dM54W6SxJWPAwQMZD/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void addItemsToPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"uris\": [\n" +
                        "        \"spotify:track:1wbQbpNwQzcrgphXzWcgw7\"\n" +
                        "    ],\n" +
                        "    \"position\": 0\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/playlists/125h9dM54W6SxJWPAwQMZD/tracks");
        response.prettyPrint();
        response.then().statusCode(201);
        Assert.assertEquals(response.statusCode(),201);
    }
    @Test
    public void removePlaylistItems(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"tracks\": [\n" +
                        "        {\n" +
                        "            \"uri\": \"spotify:track:1wbQbpNwQzcrgphXzWcgw7\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"snapshot_id\": \"AAAACygTKCtOQRhb8UZ4f2TCgiUC8UHI\"\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/125h9dM54W6SxJWPAwQMZD/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getCurrentUsersPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getUsersPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/users/smedjan/playlists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void createPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"name\": \"NewPlaylist\",\n" +
                        "    \"description\": \"New playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/users/31nxft7aiyyjnutamnyqn44afnea/playlists");
        response.prettyPrint();
        response.then().statusCode(201);
        Assert.assertEquals(response.statusCode(),201);
    }
    @Test
    public void getFeaturedPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/browse/featured-playlists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getCategoryPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/dinner/playlists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getPlaylistCoverImage(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/images");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
//    @Test
//    public void addCustomPlaylistCoverImage(){
//        Response response = given()
//                .header("accept","application/json")
//                .header("Content-Type","application/json")
//                .header("Authorization","Bearer "+ token)
//                .when()
//                .put("https://api.spotify.com/v1/playlists/125h9dM54W6SxJWPAwQMZD/images");
//        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(),200);
//    }

//    -------------------------------------------------------------------------------

    @Test
    public void getAvailableMarkets(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/markets");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    //    -------------------------------------------------------------------------------

    @Test
    public void getAvailableGenreSeeds(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    //    -------------------------------------------------------------------------------

    @Test
    public void getEpisodes(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/episodes/2rNfJMQfi9br5XmhicRACB");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getSeveralEpisodes(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/episodes?ids=2rNfJMQfi9br5XmhicRACB");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getUserSavedEpisodes(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void savedEpisodeForCurrentUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"2rNfJMQfi9br5XmhicRACB\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/episodes?ids=2rNfJMQfi9br5XmhicRACB");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void removeUserSavedEpisodes(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"2rNfJMQfi9br5XmhicRACB\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/me/episodes?ids=2rNfJMQfi9br5XmhicRACB");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void checkUserSavedEpisodes(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes/contains?ids=2rNfJMQfi9br5XmhicRACB");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

//    -----------------------------------------------------------------

    @Test
    public void getAChapter(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/chapters/0D5wENdkdwbqlrHoaJ9g29");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getSeveralChapter(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/chapters?ids=0IsXVP0JmcB2adSE338GkK%2C3ZXb8FKZGU0EHALYX6uCzU%2C0D5wENdkdwbqlrHoaJ9g29");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    //    -----------------------------------------------------------------

    @Test
    public void getSeveralBrowseCategories(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getSingleBrowseCategories(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/dinner");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    //    -----------------------------------------------------------------

    @Test
    public void getAnAudiobook(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/audiobooks/7iHfbu1YPACw6oZPAFJtqe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getSeveralAudiobook(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/audiobooks?ids=7iHfbu1YPACw6oZPAFJtqe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getAudiobookChapters(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/audiobooks/7iHfbu1YPACw6oZPAFJtqe/chapters");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getUserSavedAudiobook(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void savedAudiobookForCurrentUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .put("https://api.spotify.com/v1/me/audiobooks?ids=7iHfbu1YPACw6oZPAFJtqe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void removeUserSavedAudiobook(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .delete("https://api.spotify.com/v1/me/audiobooks?ids=7iHfbu1YPACw6oZPAFJtqe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void checkUserSavedAudiobook(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks/contains?ids=7iHfbu1YPACw6oZPAFJtqe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

//    ----------------------------------------------------------------------------------
@Test
public void getArtist(){
    Response response = given()
            .header("accept","application/json")
            .header("Content-Type","application/json")
            .header("Authorization","Bearer "+ token)
            .when()
            .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");
    response.prettyPrint();
    response.then().statusCode(200);
    Assert.assertEquals(response.statusCode(),200);
}
    @Test
    public void getSeveralArtist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/artists?ids=64KEffDW9EtZ1y2vBYgq8T");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getArtistAlbum(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/albums");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getArtistTopTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/top-tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getArtistRelatedArtist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/related-artists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

//    -----------------------------------------------------------------
@Test
public void getAlbum(){
    Response response = given()
            .header("accept","application/json")
            .header("Content-Type","application/json")
            .header("Authorization","Bearer "+ token)
            .when()
            .get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");
    response.prettyPrint();
    response.then().statusCode(200);
    Assert.assertEquals(response.statusCode(),200);
}
@Test
public void getSeveralAlbum(){
    Response response = given()
            .header("accept","application/json")
            .header("Content-Type","application/json")
            .header("Authorization","Bearer "+ token)
            .when()
            .get("https://api.spotify.com/v1/albums?ids=2Ti79nwTsont5ZHfdxIzAm");
    response.prettyPrint();
    response.then().statusCode(200);
    Assert.assertEquals(response.statusCode(),200);
}
    @Test
    public void getAlbumTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getUserSavedAlbum(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/albums");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void saveAlbumForCurrentUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"5tenzflqCjH0SmgvkUULlp\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/albums?ids=5tenzflqCjH0SmgvkUULlp");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void removeUserSavedAlbum(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"2Ti79nwTsont5ZHfdxIzAm\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/me/albums?ids=2Ti79nwTsont5ZHfdxIzAm");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void checkUserSavedAlbum(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/albums/contains?ids=2Ti79nwTsont5ZHfdxIzAm");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void getnewReleases(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/browse/new-releases");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

}
