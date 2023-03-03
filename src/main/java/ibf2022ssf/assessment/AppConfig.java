package ibf2022ssf.assessment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import ibf2022ssf.assessment.repository.ItemRepo;

public class AppConfig {
    @Bean
    @Scope("singleton")
    public ItemRepo createCart() {
    return new ItemRepo();
    }
    
	private Logger logger = Logger.getLogger(AppConfig.class.getName());

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int redisPort;

	@Value("${spring.redis.database}")
	private int redisDatabase;

	@Value("${spring.redis.username}")
	private String redisUsername;

	@Value("${spring.redis.password}")
	private String redisPassword;

	public RedisTemplate<String, String> createRedisTemplate() {

		final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
		config.setDatabase(redisDatabase);
		if (redisUsername.length() > 0)
			config.setUsername(redisUsername);
		if (redisPassword.length() > 0)
		config.setPassword(redisPassword);

		logger.info("Redis connection: host=%s, port=%d, database: %d, username: %b, password: %b"
					.formatted(redisHost, redisPort, redisDatabase, redisUsername.length() > 0, redisPassword.length() > 0));

		final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
		final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
		jedisFac.afterPropertiesSet();

		final RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisFac);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}
}


