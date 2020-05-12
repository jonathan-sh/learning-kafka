import { Producer, Message } from 'kafkajs';
import kafka from './KafkaConfig';

export default class KafkaProducer {
    producer: Producer;
    topic: string;

    constructor(topic: string) {
        this.topic = topic;
        this.producer = kafka.producer();
    }

    async send(messages: Message[]){
        await this.producer.connect()
        const record = {
            topic: this.topic,
            messages
        };
        await this.producer.send(record);
        await this.producer.disconnect();
    }

}
