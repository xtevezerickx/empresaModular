package br.com.erick.empresa.repository.util;

import java.util.HashMap;
import java.util.Map;

import org.bson.BsonType;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.DocumentCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.joda.time.DateTime;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

public class EmpresaCodecs {

    /**
     * Instantiates a new empresa codecs.
     */
    private EmpresaCodecs() {

    }

    /**
     * Codec options.
     *
     * @return the mongo client options
     */
    public static MongoClientOptions codecOptions() {
        Map<BsonType, Class<?>> replacements = new HashMap<BsonType, Class<?>>();
        replacements.put(BsonType.DATE_TIME, DateTime.class);
        BsonTypeClassMap bsonTypeClassMap = new BsonTypeClassMap(replacements);
        DocumentCodecProvider documentCodecProvider = new DocumentCodecProvider(bsonTypeClassMap);
        CodecRegistry cr = CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(new DateTimeConverter()), CodecRegistries.fromProviders(documentCodecProvider),
            MongoClient.getDefaultCodecRegistry());
        // add the new code registry has option.
        MongoClientOptions option = MongoClientOptions.builder().codecRegistry(cr).build();
        return option;
    }
}