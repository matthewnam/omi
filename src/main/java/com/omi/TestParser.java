package com.omi;
import com.omi.cme.CmeFuturesMdp3Sbev19;
import com.omi.nasdaq.NasdaqIseOrderComboFeedItchv11;
import io.pkts.Pcap;
import io.pkts.packet.Packet;
import io.pkts.packet.UDPPacket;
import io.pkts.protocol.Protocol;

import java.io.IOException;

public class TestParser {

    public static void main(String[] args) {
        try {
            //Pcap pcap = Pcap.openStream("C:\\Users\\Matthew\\Documents\\GitHub\\Data\\Nasdaq\\Ise.OrderComboFeed.v1.1\\Heartbeat.pcap");
            //Pcap pcap = Pcap.openStream("C:\\Users\\Matthew\\Documents\\GitHub\\Data\\Nasdaq\\Ise.OrderComboFeed.v1.1\\StrategyOpenClosedMessage.O.pcap");
            //Pcap pcap = Pcap.openStream("C:\\Users\\Matthew\\Documents\\GitHub\\Data\\Nasdaq\\Ise.OrderComboFeed.v1.1\\StrategyTradingActionMessage.H.pcap");
            //Pcap pcap = Pcap.openStream("C:\\Users\\Matthew Nam\\Documents\\GitHub\\Data\\Nasdaq\\Ise.OrderComboFeed.v1.1\\ComplexStrategyDirectoryMessage.R.pcap");
            Pcap pcap = Pcap.openStream("C:\\Users\\Matthew Nam\\Documents\\GitHub\\Data\\Cme\\Mdp3.Sbe.v1.9\\MdIncrementalRefreshBook.46.pcap");

            pcap.loop((final Packet packet) -> {
                if(packet.hasProtocol(Protocol.UDP)) {
                    UDPPacket udp = (UDPPacket) packet.getPacket(Protocol.UDP);

                    byte[] bytes = udp.getPayload().getArray();
                    //var packet1 = NasdaqIseOrderComboFeedItchv11.Packet.parse(bytes);
                    var packet1 = CmeFuturesMdp3Sbev19.Packet.parse(bytes);

                    System.out.println(packet1);
                }
                return true;
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
