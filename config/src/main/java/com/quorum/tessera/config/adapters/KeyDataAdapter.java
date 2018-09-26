package com.quorum.tessera.config.adapters;

import com.quorum.tessera.config.KeyData;
import com.quorum.tessera.config.keypairs.*;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Objects;

public class KeyDataAdapter extends XmlAdapter<KeyData, ConfigKeyPair> {

    public static final String NACL_FAILURE_TOKEN = "NACL_FAILURE";
    
    @Override
    public ConfigKeyPair unmarshal(final KeyData keyData) {

        //case 1, the keys are provided inline
        if (Objects.nonNull(keyData.getPrivateKey()) && Objects.nonNull(keyData.getPublicKey())) {
            return new DirectKeyPair(keyData.getPublicKey(), keyData.getPrivateKey());
        }

        //case 2, the config is provided inline
        if (keyData.getPublicKey() != null && keyData.getConfig() != null) {
            return new InlineKeypair(keyData.getPublicKey(), keyData.getConfig());
        }

        //case 3, the key vault ids are provided
        if(keyData.getAzureVaultPublicKeyId() != null && keyData.getAzureVaultPrivateKeyId() != null) {
            return new AzureVaultKeyPair(keyData.getAzureVaultPublicKeyId(), keyData.getAzureVaultPrivateKeyId());
        }

        //case 4, the keys are provided inside a file
        return new FilesystemKeyPair(keyData.getPublicKeyPath(), keyData.getPrivateKeyPath());

        //TODO Add DefaultKeyPair type to account for negative case (i.e. incorrect input?) with accompanying generic validation messages
    }

    @Override
    public KeyData marshal(final ConfigKeyPair keyData) {
        return keyData.marshal();
    }

}
