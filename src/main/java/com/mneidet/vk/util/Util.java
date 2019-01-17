package com.mneidet.vk.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mneidet.vk.model.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.StreamSupport;

public interface Util {
    final RestTemplate template = new RestTemplate();
    final ObjectMapper mapper = new ObjectMapper();

    static Profile updateProfileWithLastPhoto(final Profile profile) throws IOException {
        final StringBuilder fotoResourceUrl = new StringBuilder("https://api.vk.com/method/photos.get?");
        fotoResourceUrl.append("owner_id=");
        fotoResourceUrl.append(profile.getVkId());
        fotoResourceUrl.append("&v=5.52&");
        fotoResourceUrl.append("access_token=ec661a47ec661a47ec3c800455ec3c73b4eec66ec661a47b4ae594ad6e9d1e29d1b7da4&");
        fotoResourceUrl.append("album_id=profile");

        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> response = template.getForEntity(fotoResourceUrl.toString(), String.class);
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode root = mapper.readTree(response.getBody());
        final JsonNode responseNode = root.path("response");
        final JsonNode items = responseNode.path("items");

        Optional<JsonNode> max = StreamSupport
                .stream(items.spliterator(), false)
                .max((i1, i2) -> Long.compare(i1.path("date").asLong(), i2.path("date").asLong()));

        System.out.println("");

        final String smallPhotoLink = max.get().path("photo_130").asText();
        final String bigPhotoLink = max.get().path("photo_604").asText();

        profile.setLastSmallProfilePhotoLink(smallPhotoLink);
        profile.setLastBigProfilePhotoLink(bigPhotoLink);

        return profile;
    }

    static Profile getProfileById(final String id) throws IOException {
        final StringBuilder fotoResourceUrl = new StringBuilder("https://api.vk.com/method/users.get?access_token=ec661a47ec661a47ec3c800455ec3c73b4eec66ec661a47b4ae594ad6e9d1e29d1b7da4&v=5.52&user_ids=");
        fotoResourceUrl.append(id);

        final ResponseEntity<String> response = template
                .getForEntity(fotoResourceUrl.toString(), String.class);

        final JsonNode root = mapper.readTree(response.getBody());
        final JsonNode responseNode = root.path("response");

        Profile profile = new Profile();

        if (responseNode.isArray()) {
            for (final JsonNode profileNode : responseNode) {
                profile.setFirstName(profileNode.path("first_name").asText());
                profile.setLastName(profileNode.path("last_name").asText());
                profile.setVkId(profileNode.path("id").asText());
                break;
            }
        }

        System.out.println("");

        return updateProfileWithLastPhoto(profile);
    }

    static String getIdByNameId(String id){
        return null;
    }

}
