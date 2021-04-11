package com.omi.cme;

import io.pkts.Pcap;
import io.pkts.packet.Packet;
import io.pkts.packet.UDPPacket;
import io.pkts.protocol.Protocol;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CmeFuturesMdp3Sbev19Test {

    @Test
    void parseCmeFuturesMdp3Sbev19Packet() throws IOException {
        File pcapFile = new File("src/test/resources/Cme/Mdp3.Sbe.v1.9/MdIncrementalRefreshBook.46.pcap");
        Pcap pcap = Pcap.openStream(pcapFile);
        CmeFuturesMdp3Sbev19.Packet[] packets = new CmeFuturesMdp3Sbev19.Packet[1];

        pcap.loop((final Packet packet) -> {
            if(packet.hasProtocol(Protocol.UDP)) {
                UDPPacket udp = (UDPPacket) packet.getPacket(Protocol.UDP);
                packets[0] = CmeFuturesMdp3Sbev19.Packet.parse(udp.getPayload().getArray());
            }

            return true;
        });

        //System.out.println(packets[0].toString());
        assertEquals(packets[0].binaryPacketHeader.messageSequenceNumber.value, 1028095);
    }


}