package com.omi.cme;

import io.pkts.Pcap;
import io.pkts.packet.Packet;
import io.pkts.packet.UDPPacket;
import io.pkts.protocol.Protocol;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CmeFuturesMdp3Sbev18Test {

    @Test
    void parseCmeFuturesMdp3Sbev18Packet() throws IOException {
        File pcapFile = new File("src/test/resources/Cme/Mdp3.Sbe.v1.8/MdIncrementalRefreshTradeSummary.42.pcap");
        Pcap pcap = Pcap.openStream(pcapFile);
        CmeFuturesMdp3Sbev18.Packet[] packets = new CmeFuturesMdp3Sbev18.Packet[1];

        pcap.loop((final Packet packet) -> {
            if(packet.hasProtocol(Protocol.UDP)) {
                UDPPacket udp = (UDPPacket) packet.getPacket(Protocol.UDP);
                packets[0] = CmeFuturesMdp3Sbev18.Packet.parse(udp.getPayload().getArray());
            }

            return true;
        });

        assertEquals(packets[0].binaryPacketHeader.messageSequenceNumber.value, 17349477);
    }


}