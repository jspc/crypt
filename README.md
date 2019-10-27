# crypt

Compile/decompile messages encrypted with a UUID, and encoded into a hexadecimal sting.

## Installation

Add the following to your root `build.gradle`:

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

And the following dependency:

```gradle
	dependencies {
		implementation 'com.github.jspc:crypt:1.0.0'
	}
```

## Usage

### Decryption

To decrypt: 

```kotlin
val key = "64083981-c368-43a9-aac1-80a6412e5e79"
val message = getHexEncodedMessage()
val plaintext = Crypt().decrypt(key, message)

print("Plaintext: $plaintext")
```

### Encryption

Encryption is much the same:

```kotlin
val key = "64083981-c368-43a9-aac1-80a6412e5e79"
val plaintext = "Hello, world!"
val ciphertext = Crypt().encrypt(key, message)

print("Ciphertext: $ciphertext")
```

## Development

This project includes tests:

```bash
$ ./gradlew test
```
