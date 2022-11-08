package com.Lsc.api.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = {JsonSearch.class})
public class JsonSearchTest {
    JsonSearch search = new JsonSearch();

    @Test
    public void findPathOneFile() throws Exception {
        String onePath = search.findPath("Xmessage-color");
        Assertions.assertEquals("[\"/etc/X11/app-defaults/Xmessage-color\"]", onePath.toString());
    }

    @Test
    public void findPathMultiple() throws Exception{
        String multiplePaths = search.findPath("hook-network-manager");
        Assertions.assertEquals("[\"/etc/NetworkManager/dispatcher.d/hook-network-manager\",\"/etc/PackageKit/hook-network-manager\"]", multiplePaths.toString());
    }
    @Test
    public void noSuchFile() throws Exception{
        String noFileTest = search.findPath("nofilelikethis");
        Assertions.assertEquals("[\"No file by that name\"]", noFileTest);
    }

}
